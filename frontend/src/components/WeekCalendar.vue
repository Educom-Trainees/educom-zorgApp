<script setup>
    import { ref, watch } from "vue"
    import { QueryClient, useQuery, useQueryClient } from 'vue-query';
    import translations from '../config/nl-NL'
    import { getAppointmentsForAWeek } from "../api/appointments";
    import { getCollection } from "../api/collections";
    //import { useBreakpoints } from '../utils/WindowWidth'

    //const { width, type } = useBreakpoints()

    const week = ['sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday']
    const page = ref(0)

    function firstDayOfWeek(dateObject) {
        const dayOfWeek = dateObject.getDay();
        const firstDayOfWeek = new Date(dateObject);

        firstDayOfWeek.setDate(dateObject.getDate() - dayOfWeek);
        firstDayOfWeek.setHours(0, 0, 0, 0);

        return firstDayOfWeek;
    }

    function addDays(date, days) {
    const newDate = new Date(date.getTime() 
        + days * 24 * 60 * 60 * 1000);
   
    return newDate;
}

    const firstDayOfCurrentWeek = ref(addDays(firstDayOfWeek(new Date()), page.value * 7))

    const APPOINTMENTS = 'appointments'

    const queryClient = useQueryClient();
    const { isLoading, isError, data } = useQuery({
        queryKey: [APPOINTMENTS, firstDayOfCurrentWeek],
        queryFn: () => getCollection(APPOINTMENTS, [
            { param: 'date', value: firstDayOfCurrentWeek.value.toISOString().split('T')[0] },
            { param: 'count', value: 7 }
        ]), //might want to move customers to route meta info
        placeholderData: () => {
            // Use the smaller/list version of the customer from the CUSTOMERS
            // query as the placeholder data for this customer query
            const placeholder = queryClient
                .getQueryData([APPOINTMENTS])
            return placeholder ?? []
        }
    })

    const appointments = ref([])

    function updateAppointments(value) {
        const unsortedAppointments = [...value]
        const appointmentsByDay = [...Array(7).keys()].map((value) => {
            return { day: value, appointments: [] }
        })
        unsortedAppointments.forEach((app) => {
            const appointmentdate = new Date(app.date)
            appointmentdate.setMinutes(appointmentdate.getMinutes() + appointmentdate.getTimezoneOffset())
            const datediff = Math.floor((appointmentdate - firstDayOfCurrentWeek.value) / (1000 * 60 * 60 * 24))
            appointmentsByDay.find(abd => abd.day == datediff).appointments.push(app)
        })
        appointments.value = appointmentsByDay
    }

    if (!isLoading.value && data.value) {
        updateAppointments(data.value)
    }

    watch(data, (value) => updateAppointments(value));

    const prevPage = () => {
        page.value--
        firstDayOfCurrentWeek.value = addDays(firstDayOfWeek(new Date()), page.value * 7)

    }
    const nextPage = () => {
        page.value++
        firstDayOfCurrentWeek.value = addDays(firstDayOfWeek(new Date()), page.value * 7)
    }
</script>

<template>
    <template v-if="isLoading && !data">
        <div>Loading...</div>
    </template>
    <template v-else-if="isError || !data">
        <div>Something went wrong</div>
    </template>
    <template v-else>
        <h2 class="row justify-content-center mt-3 calendar-header">{{firstDayOfCurrentWeek.toLocaleString('nl-NL', {month: 'long', year: 'numeric'})}}</h2>
        <div class="row d-flex align-items-center mt-3">
            <div class="col-2 col-md-1">
                <button class="prev-button float-end" @click="prevPage"></button>
            </div>
            <div class="col-8 col-md-10">
                <div class="row">

                    <template v-for="item in appointments">
                        <div class="day col-12 col-md-3 col-lg zapp-gradient">
                            <div class="day-title d-flex justify-content-between mb-2">
                                <span class="float-left ms-1 text-white">
                                    {{translations[week[item.day]].slice(0,2).toUpperCase()}}
                                </span>
                                <span class="float-right me-1  text-white">
                                    {{addDays(firstDayOfCurrentWeek, item.day).getDate()}}
                                </span>
                            </div>
                            <template class="" v-for="appointment in item.appointments">
                                <RouterLink class="nav-item nav-link text-white" :to="'/' + translations.appointments + '/' + appointment.id">

                                    <div class="mb-2">
                                        <div class="name-text text-white text-wrap">
                                            {{appointment.name}}
                                        </div>
                                        <div class="address-text text-white text-wrap">
                                            {{appointment.location}}
                                        </div>
                                    </div>
                                </RouterLink>
                            </template>
                        </div>
                    </template>
                </div>
            </div>
            <div class="col-2 col-md-1">
                <button class="next-button" @click="nextPage"></button>
            </div>

        </div>
    </template>
</template>

<style scoped>
    .name-text {
        font-size: 0.9em;
        font-weight: bold;
    }

    .address-text {
        font-size: 0.75em;
    }
</style>