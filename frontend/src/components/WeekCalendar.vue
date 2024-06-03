<script setup>
    import { ref, watch, computed } from "vue"
    import { QueryClient, useQuery, useQueryClient } from 'vue-query';
    import translations from '../config/nl-NL'
    import { getAppointmentsForAWeek } from "../api/appointments";
    import { getCollection } from "../api/collections";
    import { useBreakpoints } from '../utils/WindowWidth'

    const { width, type } = useBreakpoints()
    const calendarAmount = computed(() => {
        if (['xs', 'sm'].includes(type.value)) {
            return 1
        }
        if (type.value == 'md') {
            return 4
        }
        return 7
    })

    const weekNames = ['sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday']
    const day = ref(new Date().getDay())
    const week = ref(0)

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

    const today = ref(new Date())
    const firstDayOfCurrentWeek = ref(addDays(firstDayOfWeek(new Date()), week.value * 7))

    const APPOINTMENTS = 'appointments'

    const queryClient = useQueryClient();
    const { isLoading, isError, data } = useQuery({
        queryKey: [APPOINTMENTS, firstDayOfCurrentWeek],
        queryFn: () => getCollection(APPOINTMENTS, [
            { param: 'date', value: firstDayOfCurrentWeek.value.toISOString().split('T')[0] },
            { param: 'count', value: 9 }
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
        const appointmentsByDay = [...Array(10).keys()].map((value) => {
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
        week.value += Math.floor((day.value - calendarAmount.value) / 7)
        day.value = (7 + day.value - calendarAmount.value) % 7 // need to add 7 since JS % is remainder, not modulo for some reason
        firstDayOfCurrentWeek.value = addDays(firstDayOfWeek(new Date()), week.value * 7)

    }
    const nextPage = () => {
        week.value += Math.floor((day.value + calendarAmount.value) / 7)
        day.value = (day.value + calendarAmount.value) % 7
        firstDayOfCurrentWeek.value = addDays(firstDayOfWeek(new Date()), week.value * 7)
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

                    <template v-for="item in appointments.slice(calendarAmount < 7 ? day : 0, calendarAmount < 7 ? day + calendarAmount : calendarAmount)">
                        <div class="day col-12 col-md-3 col-lg zapp-gradient">
                            <div class="day-title d-flex justify-content-between mb-2">
                                <span class="float-left ms-1 text-white">
                                    {{translations[weekNames[item.day%7]].slice(0,2).toUpperCase()}}
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