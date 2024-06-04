<script setup>
    import { ref, watch } from 'vue'
    import { useMutation, useQuery, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getIndividual, putIndividual } from '../../api/collections'
    import { compareTime, parseTime, stringifyTime } from '../../utils/Time'
    import InputForm from '../../components/InputForm.vue'
    import translations from '../../config/nl-NL'

    const TASKS = 'tasks'

    var route = useRoute();
    const id = route.params.id; //get id from route params
    const queryClient = useQueryClient();

    //vue-query to GET task by id
    const { isLoading, isError, data, error, isFetching, dataUpdatedAt } = useQuery({
        queryKey: [TASKS, id],
        queryFn: () => getIndividual(TASKS, id),
        placeholderData: () => {
            const placeholder = queryClient
                .getQueryData([TASKS])
                ?.find(t => t.id == id)
            return placeholder
        }
    })

    //refs to store data from queries
    const task = ref('')
    const startRef = ref(parseTime('00:00'))
    const endRef = ref(parseTime('00:00'))

    /**
    * function to update refs
    * @param {Object} value customer from vue-query data 
    */
    function updateTask(value) {
        task.value = { ...value }
        startRef.value = parseTime(value.startTime)
        endRef.value = parseTime(value.endTime)
    }

    //if data had already been fetched sets refs
    if (!isLoading.value && data.value) {
        updateTask(data.value)
    }

    //when data changes update refs
    watch(data, (value) => updateTask(value));

    //vue-query to PUT task
    const { isSuccess, mutate } = useMutation({
        mutationFn: putIndividual,
        onSuccess: (result) => { //fixes cached data when update is successful
            queryClient.invalidateQueries([TASKS, id])
            queryClient.cancelQueries([TASKS])
            const prevList = queryClient.getQueryData([TASKS])
            if (prevList) {
                const tempList = prevList.map(t => t.id == id ? task.value : t)
                queryClient.invalidateQueries([TASKS])
                queryClient.setQueryData([TASKS], tempList)
            }
        }
    })

    /**
    * function to update times based on input
    * @param {Object} newTime value of new time. format: {hours: 0, minutes: 0, seconds: 0}
    * @param {String} [time='start' | 'end'] specifies which time is being updated
    */
    const updateTime = (newTime, time) => {
        if (!newTime) { // newTime null means values are cleared
            startRef.value = null
            endRef.value = null
            return
        }
        switch (time) {
            case 'start':
                //sets start time, not letting it go past end time
                startRef.value = (endRef.value && compareTime(newTime, endRef.value) > 0 ? endRef.value : newTime)
                if (!endRef.value) { // if end time is not set, end time is set to same value as start
                    endRef.value = newTime
                }
                break;
            case 'end':
                //sets end time, not letting it go past start time
                endRef.value = (startRef.value && compareTime(startRef.value, newTime) > 0 ? startRef.value : newTime)
                if (!startRef.value) { // if start time is not set, start time is set to same value as end
                    startRef.value = newTime
                }
                break;
        }
    }

    //ref to toggle popup visibility
    const showPopup = ref(false)

    /**
    * function to send PUT request (error checking not implemented yet)
    */
    function putIfValid() {
        var postTask = JSON.parse(JSON.stringify(task.value))
        postTask.startTime = stringifyTime(startRef.value)
        postTask.endTime = stringifyTime(endRef.value)
        mutate({ type: TASKS, id: id, body: JSON.stringify(postTask) })
        updateTask(postTask)

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
                <InputForm type="text" :label="translations.task" v-model="task.task" id="firstName" />

                <label class="mt-3">{{translations.starttime_and_endtime}}</label>
                <div class="input-group">
                    <VueDatePicker class="form-control time-picker" input-class-name="dp-start-input" v-model="startRef" time-picker @update:model-value="(modelData) => updateTime(modelData, 'start')">
                        <template #input-icon>
                            <img class="input-slot-image" src="../../assets/clock-icon.png" />
                        </template>
                    </VueDatePicker>
                    <div class="input-group-text">&nbsp;-&nbsp;</div>
                    <VueDatePicker class="form-control time-picker" input-class-name="dp-end-input" v-model="endRef" time-picker @update:model-value="(modelData) => updateTime(modelData, 'end')">
                        <template #input-icon>
                            <img class="input-slot-image" src="../../assets/clock-icon.png" />
                        </template>
                    </VueDatePicker>
                </div>

                <InputForm type="number" :label="translations.duration" min="0" v-model="task.duration" />

                <div class="position-bottom-right mb-4 me-4">
                    <button type="button" :class="'toggle-button me-2' + (task.active ? ' active' : '')" data-bs-toggle="button" aria-pressed="true" @click="() => task.active = !task.active">{{task.active ? translations.deactivate : translations.activate}}</button>
                    <button type="submit" class="default-button" @click="putIfValid">{{translations.save}}</button>
                </div>
            </div>
        </div>

        <div id="popup" :class="showPopup ? 'saved-pop-up' : 'invisible'">{{translations.task + ' ' + translations.saved + '!'}}</div>
    </template>
</template>