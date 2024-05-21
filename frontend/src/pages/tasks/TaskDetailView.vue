<script setup>
    import { useMutation, useQuery, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getIndividual, putIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import { ref, toRaw, toRef, watch } from 'vue'
    import translations from '../../config/nl-NL'



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
    function updateTask(value) {
        task.value = { ...value }
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

    const postIfValid = () => {
        //console.log(firstName, lastName, address, postalCode, residence)
        mutate({ id: id, task: JSON.stringify(task.value) })
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
            <form class="offset-1 col-10" @submit.prevent="postIfValid">
                <InputForm type="text" :label="translations.task" v-model="task.task" id="firstName" />
                
                <button type="submit" class="position-bottom-right default-button mb-4 me-4">{{translations.save}}</button>
            </form>
        </div>
    </template>
</template>