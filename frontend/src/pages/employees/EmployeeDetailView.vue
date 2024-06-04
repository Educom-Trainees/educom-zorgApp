<script setup>
    import { useMutation, useQuery, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getIndividual, putIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import WorkSchedule from './WorkSchedule.vue'
    import { ref, toRaw, toRef, watch } from 'vue'
    import translations from '../../config/nl-NL'
    import { ciEquals } from '../../utils/StringComparison'
    import { parseTime, stringifyTime } from '../../utils/Time'

    const EMPLOYEES = 'employees'

    var route = useRoute();
    const id = route.params.id; //get id from route params
    const queryClient = useQueryClient();

    //vue-query to GET employee by id
    const { isLoading, isError, data, error, isFetching, dataUpdatedAt } = useQuery({
        queryKey: [EMPLOYEES, id],
        queryFn: () => getIndividual(EMPLOYEES, id),
        placeholderData: () => {
            const placeholder = queryClient
                .getQueryData([EMPLOYEES])
                ?.find(e => e.id == id)
            return placeholder
        }
    })

    //refs to store data from queries
    const employee = ref('')
    const scheduleRef = ref([])

    /**
    * function to update refs
    * @param {Object} value customer from vue-query data 
    */
    function updateEmployee(value) {
        employee.value = { ...value }

        //fill schedule with any times that employee has
        const weekdays = ['sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday']
        scheduleRef.value = weekdays.map((day) => {
            const daySchedule = value.workSchedule.find((d) => ciEquals(d.day, day))
            return { day: day, start_shift: daySchedule ? parseTime(daySchedule.start_shift) : null, end_shift: daySchedule ? parseTime(daySchedule.end_shift) : null }
        })
    }

    //if data had already been fetched sets refs
    if (!isLoading.value && data.value) {
        updateEmployee(data.value)
    }

    //when data changes update refs
    watch(data, (value) => updateEmployee(value));

    //vue-query to PUT employee
    const { isSuccess, mutate } = useMutation({
        mutationFn: putIndividual,
        onSuccess: (result) => { //fixes cached data when update is successful
            queryClient.invalidateQueries([EMPLOYEES, id])
            queryClient.cancelQueries([EMPLOYEES])
            const prevList = queryClient.getQueryData([EMPLOYEES])
            if (prevList) {
                const tempList = prevList.map(e => e.id == id ? employee.value : e)
                queryClient.invalidateQueries([EMPLOYEES])
                queryClient.setQueryData([EMPLOYEES], tempList)
            }
        }
    })

    /**
    * function to convert times back to string format
    * @param {Array} schedule
    * @returns {Array} schedule with updated time values
    */
    function fixSchedule(schedule) {
        return schedule.map((day) => {
            day.start_shift = stringifyTime(day.start_shift)
            day.end_shift = stringifyTime(day.end_shift)
            return day
        }).filter(d => d.start_shift)
    }

    //ref to toggle popup visibility
    const showPopup = ref(false)

    /**
    * function to send PUT request (error checking not implemented yet)
    */
    function putIfValid() {
        var postEmployee = JSON.parse(JSON.stringify(employee.value))
        postEmployee.workSchedule = fixSchedule(scheduleRef.value)
        mutate({ type: EMPLOYEES, id: id, body: JSON.stringify(postEmployee) })
        updateEmployee(postEmployee)

        //show confirmation
        showPopup.value = true
        setTimeout(() => showPopup.value = false, 2500);
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
        <div class="row">
            <div class="offset-1 col-10">
                <InputForm type="text" :label="translations.firstName" v-model="employee.name" id="name" />
                <InputForm type="text" :label="translations.lastName" v-model="employee.lastName" id="lastName" />
                <InputForm type="text" :label="translations.userName" v-model="employee.username" id="username" />
                <InputForm type="text" :label="translations.address" v-model="employee.address" id="address" />
                <div class="row">
                    <InputForm class="col-6" type="text" :label="translations.postalCode" v-model="employee.postalcode" id="postalcode" />
                    <InputForm class="col-6" type="text" :label="translations.residence" v-model="employee.residence" id="residence" />
                </div>

                <WorkSchedule v-model="scheduleRef" />
                <div class="mt-5 mb-5 pt-5"></div>

                <div class="position-bottom-right mb-4 me-4">
                    <button type="button" :class="'toggle-button me-2' + (employee.active ? ' active' : '')" data-bs-toggle="button" aria-pressed="true" @click="() => employee.active = !employee.active">{{employee.active ? translations.deactivate : translations.activate}}</button>
                    <button type="submit" class="default-button" @click="putIfValid">{{translations.save}}</button>
                </div>
            </div>
        </div>

        <div id="popup" :class="showPopup ? 'saved-pop-up' : 'invisible'">{{translations.employee + ' ' + translations.saved + '!'}}</div>
    </template>
</template>