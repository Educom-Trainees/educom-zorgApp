<script setup>
    import { useMutation, useQuery } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import { ref, toRaw, toRef, watch } from 'vue'
    import translations from '../../config/nl-NL'
    import { putTask } from '../../api/tasks'

    var route = useRoute();
    const id = route.params.id;

    const { isLoading, isError, data, error, isFetching, dataUpdatedAt } = useQuery({
        queryKey: ['tasks', id],
        queryFn: () => getIndividual('tasks', id), //might want to move customers to route meta info
    })

    const task = ref(JSON.parse(JSON.stringify(data?.value ? data.value : '')))
    watch(data, (value) => {
        console.log(value)
        task.value = JSON.parse(JSON.stringify(value))
    });

    const {isSuccess, mutate } = useMutation({
        mutationFn: putTask,
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
                <InputForm type="text" :label="translations.firstName" v-model="task.task" id="firstName" />
                
                <button type="submit" class="position-bottom-right default-button mb-4 me-4">{{translations.save}}</button>
            </form>
        </div>
    </template>
</template>