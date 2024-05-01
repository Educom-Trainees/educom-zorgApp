<script setup>
    import { ref } from "vue"
    import { QueryClient, useQuery, useQueryClient } from 'vue-query';
    import translations from '../config/nl-NL'
    import { getAppointmentsForAWeek } from "../api/appointments";
    



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

    const firstDayOfCurrentWeek = firstDayOfWeek(new Date())
    
    const info = [
        { name: "mr. guy", address: "real street 1, AB12345" },
        { name: "mr. guy", address: "real street 1, AB12345" },
        { name: "mr. guy", address: "real street 1, AB12345" },
        { name: "mr. guy", address: "real street 1, AB12345" }
    ]

    const fetchWeek = () => {
        const day = addDays(firstDayOfCurrentWeek, page * 7);
        const d = getAppointmentsForAWeek(day);
        return d;
    }

    const { isLoading, isError, data, error, isFetching } = useQuery({
        queryKey: ['calendar', page],
        queryFn: fetchWeek,
    })

    const prevPage = () => {
        page.value--
    }
    const nextPage = () => {
        page.value++
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
        <div class="d-flex justify-content-center align-items-center mt-3">
            <button class="prev-button ms-1 me-5" @click="prevPage"></button>
            <template class="calendar w-50" v-for="item in data">
                <div class="day">
                    <div class="day-title d-flex justify-content-between">
                        <span class="float-left ms-1">
                            {{translations[week[item.day]].slice(0,2).toUpperCase()}}
                        </span>
                        <span class="float-right me-1">
                            {{addDays(firstDayOfCurrentWeek, item.day).getDate()}}
                        </span>
                    </div>
                    <template v-for="appointment in item.appointments">
                        <div>
                            {{appointment.name}}
                        </div>
                        <div>
                            {{appointment.address}}
                        </div>
                    </template>
                </div>
            </template>

            <button class="next-button ms-5 me-1" @click="nextPage"></button>
        </div>
    </template>
</template>