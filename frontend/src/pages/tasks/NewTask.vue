<script setup>
    import { ref } from 'vue'
    import { useMutation, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { postIndividual } from '../../api/collections'
    import { compareTime, stringifyTime } from '../../utils/Time'
    import InputForm from '../../components/InputForm.vue'
    import translations from '../../config/nl-NL'

    const TASKS = 'tasks'

    var route = useRoute();
    const id = route.params.id; //get id from route params

    const queryClient = useQueryClient();

    //ref for new task
    const task = ref({
        id: 0,
        task: '',
        duration: 0,
        startTime: null,
        endTime: null,
        active: true,
    })

    //vue-query to POST task
    const { isSuccess, mutate } = useMutation({
        mutationFn: postIndividual,
        onSuccess: (result) => {
            queryClient.invalidateQueries([TASKS])
        }
    })

    /**
    * function to update times based on input
    * @param {Object} newTime value of new time. format: {hours: 0, minutes: 0, seconds: 0}
    * @param {String} [time='start' | 'end'] specifies which time is being updated
    */
    const updateTime = (newTime, time) => {
        if (!newTime) { // newTime null means values are cleared
            task.value.startTime = null
            task.value.endTime = null
            return
        }
        switch (time) {
            case 'start':
                //sets start time, not letting it go past end time
                task.value.startTime = (task.value.endTime && compareTime(newTime, task.value.endTime) > 0 ? task.value.endTime : newTime)
                if (!task.value.endTime) { // if end time is not set, end time is set to same value as start
                    task.value.endTime = newTime
                }
                break;
            case 'end':
                //sets end time, not letting it go past start time
                task.value.endTime = (task.value.startTime && compareTime(task.value.startTime, newTime) > 0 ? task.value.startTime : newTime)
                if (!task.value.startTime) { // if start time is not set, start time is set to same value as end
                    task.value.startTime = newTime
                }
                break;
        }
    }

    /**
    * function to send POST request (error checking not implemented yet)
    */
    function postIfValid() {
        task.value.startTime = stringifyTime(task.value.startTime)
        task.value.endTime = stringifyTime(task.value.endTime)
        mutate({ type: TASKS, body: JSON.stringify(task.value) })
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
            <InputForm type="text" :label="translations.task" v-model="task.task" id="task" />

            <label class="mt-3">{{translations.starttime_and_endtime}}</label>
            <div class="input-group">
                <VueDatePicker class="form-control time-picker" input-class-name="dp-start-input" v-model="task.startTime" time-picker @update:model-value="(modelData) => updateTime(modelData, 'start')">
                    <template #input-icon>
                        <img class="input-slot-image" src="../../assets/clock-icon.png" />
                    </template>
                </VueDatePicker>
                <div class="input-group-text">&nbsp;-&nbsp;</div>
                <VueDatePicker class="form-control time-picker" input-class-name="dp-end-input" v-model="task.endTime" time-picker @update:model-value="(modelData) => updateTime(modelData, 'end')">
                    <template #input-icon>
                        <img class="input-slot-image" src="../../assets/clock-icon.png" />
                    </template>
                </VueDatePicker>
            </div>

            <InputForm type="number" :label="translations.duration" min="0" v-model="task.duration" />
        </div>
    </div>
</template>