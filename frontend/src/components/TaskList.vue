<script setup>
    import translations from '../config/nl-NL'
    import TaskListAdd from './TaskListAdd.vue'
    import { ref, computed } from "vue";

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

    const timeslots = [
        {name: 'morning', start: '06:00', end: '11:00'},
        {name: 'midday', start: '11:00', end: '14:00'},
        {name: 'afternoon', start: '14:00', end: '22:00'},
    ]

    const currentTimeslot = computed(() => {
        const tasks = props.modelValue
        var earliestStart = null
        var latestEnd = null
        tasks.forEach((t) => {
            earliestStart = (earliestStart ? (t.startTime < earliestStart ? t.startTime : earliestStart) : t.startTime)
            latestEnd = (latestEnd ? (t.endTime > latestEnd ? t.endTime : latestEnd) : t.endTime)
        })
        var timeslot = null
        timeslots.forEach((ts) => {
            if (ts.start <= earliestStart && ts.end >= latestEnd) {
                timeslot = ts
            }
        })
        return timeslot
    })

    const headers = ['task', 'additionalInfo', 'time'];
    if (props.appointmentStarted) {
        headers.push('completed')
        headers.push('noteEmployee')
    } else {
        headers.push(' ')
    }

    const addTask = (task) => {
        console.log('receiving')
        console.log(task)
        props.modelValue.push(task)
    }

    const removeTask = (task_index) => {
        props.modelValue.splice(task_index, 1)
    }

    const tempref = ref('')

    const updateTime = (event, index, time) => {
        const appointment = props.modelValue.at(index)
        switch (time) {
            case 'start':
                appointment.startTime = (event.target.value > appointment.endTime ? appointment.endTime : event.target.value)
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
                appointment.endTime = (event.target.value < appointment.startTime ? appointment.startTime : event.target.value)
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
    {{'Timeslot: ' + (currentTimeslot ? currentTimeslot.name : 'none')}}
    <table v-if="props.modelValue.length > 0" class="table">
        <thead>
            <tr>
                <th v-for="key in headers">{{translations[key] ?? key}}</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(item, index) in props.modelValue">
                <td v-for="key in headers">
                    <div v-if="key == 'time'">
                        <form class="form-inline">
                            <div class="input-group">
                                <input class="input-group-prepend form-control" type="time" v-model="item.startTime" @change="updateTime($event, index, 'start')"/>
                                <div class="input-group-text">&nbsp;-&nbsp;</div>
                                <input class="input-group-append form-control" type="time" v-model="item.endTime" @change="updateTime($event, index, 'end')"/>
                            </div>
                        </form>
                    </div>
                    <div v-else-if="key == ' '">
                        <button class="btn btn-danger bi bi-trash" @click="removeTask(index)"></button>
                    </div>
                    <div v-else>
                        <input class="form-control" v-model="item[key]"/>
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