<script setup>
    import { useMutation, useQuery, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getIndividual, putIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import { ref, toRaw, toRef, watch } from 'vue'
    import translations from '../../config/nl-NL'
    import { compareTime, parseTime, stringifyTime } from '../../utils/Time'

    const TASKS = 'tasks'

    var route = useRoute();
    const id = route.params.id;
    const queryClient = useQueryClient();
    const { isLoading, isError, data, error, isFetching, dataUpdatedAt } = useQuery({
        queryKey: [TASKS, id],
        queryFn: () => getIndividual(TASKS, id), //might want to move customers to route meta info
        placeholderData: () => {
            // Use the smaller/list version of the customer from the CUSTOMERS
            // query as the placeholder data for this customer query
            const placeholder = queryClient
                .getQueryData([TASKS])
                ?.find(t => t.id == id)
            return placeholder
        }
    })

    const task = ref('')
    const startRef = ref(parseTime('00:00'))
    const endRef = ref(parseTime('00:00'))
    function updateTask(value) {
        task.value = { ...value }
        startRef.value = parseTime(value.startTime)
        endRef.value = parseTime(value.endTime)
    }

    if (!isLoading.value && data.value) {
        updateTask(data.value)
    }
    watch(data, (value) => updateTask(value));

    const { isSuccess, mutate } = useMutation({
        mutationFn: putIndividual,
        onSuccess: (result) => {
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

    const updateTime = (newTime, time) => {
        console.log(newTime, time, task)
        if (!newTime) { // newTime null means values are cleared
            startRef.value = null
            endRef.value = null
            return
        }
        switch (time) {
            case 'start':
                startRef.value = (endRef.value && compareTime(newTime, endRef.value) > 0 ? endRef.value : newTime)
                if (!endRef.value) {
                    endRef.value = newTime
                }
                break;
            case 'end':
                endRef.value = (startRef.value && compareTime(startRef.value, newTime) > 0 ? startRef.value : newTime)
                if (!startRef.value) {
                    startRef.value = newTime
                }
                break;
        }
    }

    const postIfValid = () => {
        var postTask = JSON.parse(JSON.stringify(task.value))
        postTask.startTime = stringifyTime(startRef.value)
        postTask.endTime = stringifyTime(endRef.value)
        mutate({ type: TASKS, id: id, body: JSON.stringify(postTask) })
        updateTask(postTask)
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
                    <button type="submit" class="default-button" @click="postIfValid">{{translations.save}}</button>
                </div>
            </div>
        </div>
    </template>
</template>