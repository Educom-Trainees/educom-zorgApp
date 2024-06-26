<script setup>
    import { ciEquals } from '../../utils/StringComparison'
    import { compareTime } from '../../utils/Time'
    import translations from '../../config/nl-NL'

    const props = defineProps({
        modelValue: { //binds to v-model
            type: Array,
            default: [],
        },
    })

    /**
    * function to update times based on input
    * @param {Object} newTime value of new time. format: {hours: 0, minutes: 0, seconds: 0}
    * @param {String} day day index
    * @param {String} [time='start' | 'end'] specifies which time is being updated
    */
    function updateTime(newTime, day, time) {
        console.log(newTime, time)
        const daySchedule = props.modelValue.find((d) => ciEquals(d.day, day))
        if (!newTime) { // newTime null means values are cleared
            daySchedule.start_shift = null
            daySchedule.end_shift = null
            return
        }
        switch (time) {
            case 'start':
                //sets start time, not letting it go past end time
                daySchedule.start_shift = (daySchedule.end_shift && compareTime(newTime, daySchedule.end_shift) > 0 ? daySchedule.end_shift : newTime)
                if (!daySchedule.end_shift) { // if end time is not set, end time is set to same value as start
                    daySchedule.end_shift = newTime
                }
                break;
            case 'end':
                //sets end time, not letting it go past start time
                daySchedule.end_shift = (daySchedule.start_shift && compareTime(daySchedule.start_shift, newTime) > 0 ? daySchedule.start_shift : newTime)
                if (!daySchedule.start_shift) { // if start time is not set, start time is set to same value as end
                    daySchedule.start_shift = newTime
                }
                break;
        }
    }
</script>

<template>
    <label class="mt-3">{{translations.workSchedule}}</label>
    <div v-for="day in props.modelValue">
        <div class="row">
            <div class="input-group mt-2">
                <div class="input-group-text day-label float-end">{{translations[day.day]}}</div>
                <VueDatePicker class="form-control time-picker" input-class-name="dp-start-input" v-model="day.start_shift" time-picker @update:model-value="(modelData) => updateTime(modelData, day.day, 'start')">
                    <template #input-icon>
                        <img class="input-slot-image" src="../../assets/clock-icon.png" />
                    </template>
                </VueDatePicker>
                <div class="input-group-text">&nbsp;-&nbsp;</div>
                <VueDatePicker class="form-control time-picker" input-class-name="dp-end-input" v-model="day.end_shift" time-picker @update:model-value="(modelData) => updateTime(modelData, day.day, 'end')">
                    <template #input-icon>
                        <img class="input-slot-image" src="../../assets/clock-icon.png" />
                    </template>
                </VueDatePicker>
            </div>
        </div>
        <div></div>
    </div>
</template>

<style scoped>
    .day-label {
        justify-content: flex-end;
        min-width: 7rem;
    }
</style>