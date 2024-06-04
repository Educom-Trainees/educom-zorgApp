<script setup>
    import { parseTime, compareTime } from '../utils/Time'
    import InputForm from './InputForm.vue'
    import TaskListAdd from './TaskListAdd.vue'
    import translations from '../config/nl-NL'

    const props = defineProps({
        modelValue: { //modelValue binds to v-model
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

    //headers for task list (different depending on whether appointment has started or not)
    const headers = ['task', 'additionalInfo', 'expected_time', 'duration'];
    if (props.appointmentStarted) {
        headers.push('completed')
        headers.push('noteEmployee')
    } else {
        headers.push(' ') //empty header used for delete and add buttons
    }

    /**
    * function to add a task to list (used in TaskListAdd.vue)
    * @param {Object} task
    */
    function addTask(task) {
        task.startTime = parseTime(task.startTime)
        task.endTime = parseTime(task.endTime)

        props.modelValue.push(task)
    }

    /**
    * function to remove a task from list
    * @param {Number} task_index
    */
    function removeTask(task_index) {
        props.modelValue.splice(task_index, 1)
    }

    /**
    * function to update times based on input
    * @param {Object} newTime value of new time. format: {hours: 0, minutes: 0, seconds: 0}
    * @param {Number} index task index
    * @param {String} [time='start' | 'end'] specifies which time is being updated
    */
    const updateTime = (newTime, index, time) => {
        const appointment = props.modelValue.at(index)
        if (!newTime) { // newTime null means values are cleared
            appointment.startTime = null
            appointment.endTime = null
            return
        }
        switch (time) {
            case 'start':
                //sets start time, not letting it go past end time
                appointment.startTime = (appointment.endTime && compareTime(newTime, appointment.endTime) > 0 ? appointment.endTime : newTime)
                if (!appointment.endTime) { // if end time is not set, end time is set to same value as start
                    appointment.endTime = appointment.startTime
                }
                break;
            case 'end':
                //sets end time, not letting it go past start time
                appointment.endTime = (appointment.startTime && compareTime(appointment.startTime, newTime) > 0 ? appointment.startTime : newTime)
                if (!appointment.startTime) { // if start time is not set, start time is set to same value as end
                    appointment.startTime = appointment.endTime
                }
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
                                <VueDatePicker class="form-control time-picker" input-class-name="dp-start-input" v-model="item.startTime" time-picker @update:model-value="(modelData) => updateTime(modelData, index, 'start')">
                                    <template #input-icon>
                                        <img class="input-slot-image" src="../assets/clock-icon.png" />
                                    </template>
                                </VueDatePicker>
                                <div class="input-group-text">&nbsp;-&nbsp;</div>
                                <VueDatePicker class="form-control time-picker" input-class-name="dp-end-input" v-model="item.endTime" time-picker @update:model-value="(modelData) => updateTime(modelData, index, 'end')">
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