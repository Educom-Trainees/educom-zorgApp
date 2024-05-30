<script setup>
    import translations from '../config/nl-NL'
    import TaskListAdd from './TaskListAdd.vue'
    import InputForm from './InputForm.vue'
    import { ref, computed, toRef } from "vue";
    import { parseTime, compareTime } from '../utils/Time';

    const props = defineProps({
        modelValue: {
            type: Array,
            default: [],
        },
        appointmentStarted: {
            type: Boolean,
            default: false,
        },
        appointmentFinished: {
            type: Boolean,
            default: false,
        },
        defaultOptions: Array,
    })

    const headers = ['task', 'additionalInfo', 'expected_time', 'duration'];
    if (props.appointmentStarted) {
        headers.push('completed')
        headers.push('noteEmployee')
    } else {
        headers.push(' ')
    }

    const addTask = (task) => {
        console.log('receiving')
        console.log(task)
        task.startTime = parseTime(task.startTime)
        task.endTime = parseTime(task.endTime)

        props.modelValue.push(task)
    }

    const removeTask = (task_index) => {
        props.modelValue.splice(task_index, 1)
    }

    const tempref = ref('')

    const updateTime = (newTime, index, time) => {
        console.log(newTime, index, time)
        const appointment = props.modelValue.at(index)
        if (!newTime) { // newTime null means values are cleared
            appointment.startTime = null
            appointment.endTime = null
            return
        }
        switch (time) {
            case 'start':
                appointment.startTime = (appointment.endTime && compareTime(newTime, appointment.endTime) > 0 ? appointment.endTime : newTime)
                if (!appointment.endTime) {
                    appointment.endTime = appointment.startTime
                }
                //console.log(appointment.startTime)
                //if (currentTimeslot) { // not functional, might not want it to be
                //    console.log(currentTimeslot.value)
                //    appointment.startTime = (appointment.startTime > currentTimeslot.value.start ? appointment.startTime : currentTimeslot.value.start)
                //}
                break;
            case 'end':
                appointment.endTime = (appointment.startTime && compareTime(appointment.startTime, newTime) > 0 ? appointment.startTime : newTime)
                if (!appointment.startTime) {
                    appointment.startTime = appointment.endTime
                }
                //if (currentTimeslot) {
                //    appointment.endTime = (appointment.endTime < currentTimeslot.value.end ? appointment.endTime : currentTimeslot.value.end)
                //}
                break;
        }
    }

    

</script>

<template>
    <table class="table">
        <thead>
            <tr>
                <th v-for="key in headers">{{translations[key] ?? key}}</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(item, index) in props.modelValue">
                <td v-for="key in headers">
                    <div v-if="key == 'expected_time'">
                        <form class="form-inline">
                            <div class="input-group">
                                <VueDatePicker class="form-control time-picker" input-class-name="dp-start-input" v-model="item.startTime" time-picker text-input @update:model-value="(modelData) => updateTime(modelData, index, 'start')">
                                    <template #input-icon>
                                        <img class="input-slot-image" src="../assets/clock-icon.png" />
                                    </template>
                                </VueDatePicker>
                                <div class="input-group-text">&nbsp;-&nbsp;</div>
                                <VueDatePicker class="form-control time-picker" input-class-name="dp-end-input" v-model="item.endTime" time-picker @change="updateTime($event, index, 'end')">
                                    <template #input-icon>
                                        <img class="input-slot-image" src="../assets/clock-icon.png" />
                                    </template>
                                </VueDatePicker>
                            </div>
                        </form>
                    </div>
                    <div v-else-if="key == ' '">
                        <button class="red-button px-3 py-1 bi bi-trash" @click="removeTask(index)"></button>
                    </div>
                    <div v-else-if="key == 'duration'">
                        <InputForm type="number" min="0" v-model="item[key]" />
                    </div>
                    <div v-else>
                        <InputForm type="text" v-model="item[key]" />
                    </div>
                </td>
            </tr>
            <tr v-if="!appointmentStarted">
                <TaskListAdd :options="defaultOptions" :addTask="addTask"></TaskListAdd>
                
            </tr>
        </tbody>
    </table>
    <div v-if="!appointmentStarted" class="mt-5 mb-5 pt-5"></div>
</template>

<style scoped>
    .time-picker {
        padding: 0;
        border: none;
    }

    .input-slot-image {
        height: 16px;
        width: auto;
        margin-bottom: 4px;
        margin-left: 8px;
    }
</style>