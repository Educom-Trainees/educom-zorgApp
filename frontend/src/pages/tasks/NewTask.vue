<script setup>
    import { ref } from 'vue'
    import { useMutation, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { postIndividual } from '../../api/collections'
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
    * function to send POST request (error checking not implemented yet)
    */
    function postIfValid() {
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
        <form class="offset-1 col-10" @submit.prevent="postIfValid">
            <InputForm type="text" :label="translations.task" v-model="task.task" id="task" />
        </form>
    </div>
</template>