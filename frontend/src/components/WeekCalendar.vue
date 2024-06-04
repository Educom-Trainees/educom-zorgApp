<script setup>
    import { ref, watch, computed } from "vue"
    import { useQuery, useQueryClient } from 'vue-query'
    import { getCollection } from "../api/collections"
    import { useBreakpoints } from '../utils/WindowWidth'
    import translations from '../config/nl-NL'

    //calculate viewport sizes
    const { width, type } = useBreakpoints()
    const calendarAmount = computed(() => {
        if (['xs', 'sm'].includes(type.value)) {
            return 1 //only show 1 day on small screens
        }
        if (type.value == 'md') {
            return 4 //show 4 days on medium screens
        }
        return 7 //otherwise show full week
    })

    const weekNames = ['sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday']

    //refs to track which items are shown
    const day = ref(new Date().getDay()) //number 0-6 based on day
    const week = ref(0) //week number starts at 0 for current week (i.e. -1 is previous week, +1 is next week)

    /**
    * function to calculate the date of first day of the week (sunday)
    * @param {Date} dateObject
    * @returns {Date} date object of first day of the week which dateObject is in
    */
    function firstDayOfWeek(dateObject) {
        const dayOfWeek = dateObject.getDay(); //number 0-6
        const firstDayOfWeek = new Date(dateObject);

        firstDayOfWeek.setDate(dateObject.getDate() - dayOfWeek);
        firstDayOfWeek.setHours(0, 0, 0, 0);

        return firstDayOfWeek;
    }

    /**
    * function to add a number of days to an existing date object
    * @param {Date} date
    * @param {Number} days can be negative
    * @returns {Date} date which is {days} number of days later
    */
    function addDays(date, days) {
        const newDate = new Date(date.getTime() 
            + days * 24 * 60 * 60 * 1000);
   
        return newDate;
    }

    //ref to track the current week
    const firstDayOfCurrentWeek = ref(addDays(firstDayOfWeek(new Date()), week.value * 7))

    const APPOINTMENTS = 'appointments'

    // vue-query to GET appointments by date
    const queryClient = useQueryClient();
    const { isLoading, isError, data } = useQuery({
        queryKey: [APPOINTMENTS, firstDayOfCurrentWeek],
        queryFn: () => getCollection(APPOINTMENTS, [
            { param: 'date', value: firstDayOfCurrentWeek.value.toISOString().split('T')[0] },
            { param: 'count', value: 9 } //gets 9 extra days for a total of 10 (extras used for 4-day display)
        ]),
        placeholderData: () => {
            const placeholder = queryClient
                .getQueryData([APPOINTMENTS])
            return placeholder ?? []
        }
    })

    //ref to hold appointment data
    const appointments = ref([])

    /**
    * function to update appointments ref
    * @param {Array} value list of appointments from vue-query data
    */
    function updateAppointments(value) {
        const unsortedAppointments = [...value]
        //create variable to store appointments by day
        const appointmentsByDay = [...Array(10).keys()].map((value) => {
            return { day: value, appointments: [] }
        })
        //function which fixes any timezone offsets and then sorts appointments by day
        unsortedAppointments.forEach((app) => {
            const appointmentdate = new Date(app.date)
            appointmentdate.setMinutes(appointmentdate.getMinutes() + appointmentdate.getTimezoneOffset()) //remove timezone offset to avoid days being off by one
            const datediff = Math.floor((appointmentdate - firstDayOfCurrentWeek.value) / (1000 * 60 * 60 * 24))
            appointmentsByDay.find(abd => abd.day == datediff).appointments.push(app)
        })
        appointments.value = appointmentsByDay
    }

    //if data had already been fetched sets appointments ref
    if (!isLoading.value && data.value) {
        updateAppointments(data.value)
    }

    //when data changes update appointments ref
    watch(data, (value) => updateAppointments(value));

    /**
    * function to go to previous page
    * updates week and day value based on how many days are being shown
    */
    function prevPage() {
        week.value += Math.floor((day.value - calendarAmount.value) / 7)
        day.value = (7 + day.value - calendarAmount.value) % 7 // need to add 7 since JS % is remainder, not modulo for some reason | alternatively you could take remainder, add 7 then remainder again for more generic solution
        firstDayOfCurrentWeek.value = addDays(firstDayOfWeek(new Date()), week.value * 7)

    }

    /**
    * function to go to next page
    * updates week and day value based on how many days are being shown
    */
    function nextPage() {
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