<script setup>
    import { ref } from 'vue'
    import { useMutation, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { postIndividual } from '../../api/collections'
    import { stringifyTime } from '../../utils/Time'
    import InputForm from '../../components/InputForm.vue'
    import WorkSchedule from './WorkSchedule.vue'
    import translations from '../../config/nl-NL'

    const EMPLOYEES = 'employees'

    var route = useRoute();
    const id = route.params.id; //get id from route params

    const queryClient = useQueryClient();
    
    const weekdays = ['sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday']

    //ref for new employee
    const employee = ref({
        id: 0,
        name: '',
        lastName: '',
        username: '',
        address: '',
        postalcode: '',
        residence: '',
        active: true,
        workSchedule:
            weekdays.map((day) => {
                return { day: day, start_shift: null, end_shift: null }
            }),
    })

    //vue-query to POST employee
    const { isSuccess, mutate } = useMutation({
        mutationFn: postIndividual,
        onSuccess: (result) => {
            queryClient.invalidateQueries([EMPLOYEES])
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

    /**
    * function to send POST request (error checking not implemented yet)
    */
    function postIfValid() {
        employee.value.workSchedule = fixSchedule(employee.value.workSchedule)
        mutate({ type: EMPLOYEES, body: JSON.stringify(employee.value) })
    }

    /**
    * wrapper function to be used by parent components
    */
    function onConfirm() {
        postIfValid();
    }

    defineExpose({ onConfirm }) //expose function so it can be used by parent components
</script>

<template>
    <div class="row">
        <div class="offset-1 col-10">
            <InputForm type="text" :label="translations.firstName" v-model="employee.name" id="name" />
            <InputForm type="text" :label="translations.lastName" v-model="employee.lastName" id="lastName" />
            <InputForm type="text" :label="translations.userName" v-model="employee.username" id="username" />
            <InputForm type="text" :label="translations.address" v-model="employee.address" id="address" />
            <div class="row">
                <InputForm class="col-12 col-md-6" type="text" :label="translations.postalCode" v-model="employee.postalcode" id="postalcode" />
                <InputForm class="col-12 col-md-6" type="text" :label="translations.residence" v-model="employee.residence" id="residence" />
            </div>
            <WorkSchedule v-model="employee.workSchedule" />
        </div>
    </div>
</template>