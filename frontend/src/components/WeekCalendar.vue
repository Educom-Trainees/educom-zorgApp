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
        <div class="row d-flex align-items-center mt-3">
            <div class="col-2 col-md-1">
                <button class="prev-button float-end" @click="prevPage"></button>
            </div>
            <div class="col-8 col-md-10">
                <div class="row">

                    <template v-for="item in data">
                        <div class="day col-12 col-md-3 col-lg zapp-gradient">
                            <div class="day-title d-flex justify-content-between">
                                <span class="float-left ms-1 text-white">
                                    {{translations[week[item.day]].slice(0,2).toUpperCase()}}
                                </span>
                                <span class="float-right me-1  text-white">
                                    {{addDays(firstDayOfCurrentWeek, item.day).getDate()}}
                                </span>
                            </div>
                            <template class="" v-for="appointment in item.appointments">
                                <div class="mb-2">
                                    <div class="name-text text-white text-wrap">
                                        {{appointment.name}}
                                    </div>
                                    <div class="address-text text-white text-wrap">
                                        {{appointment.address}}
                                    </div>
                                </div>
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