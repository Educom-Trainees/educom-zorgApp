<script setup>
    import { useMutation, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { postIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import { ref } from 'vue'
    import translations from '../../config/nl-NL'

    const TASKS = 'tasks'

    var route = useRoute();
    const id = route.params.id;

    const queryClient = useQueryClient();

    const task = ref({
        id: 0,
        task: '',
        active: true,
    })

    const { isSuccess, mutate } = useMutation({
        mutationFn: postIndividual,
        onSuccess: (result) => {
            queryClient.invalidateQueries([TASKS])
        }
    })

    const postIfValid = () => {
        //console.log(firstName, lastName, address, postalCode, residence)
        mutate({ type: TASKS, body: JSON.stringify(task.value) })
    }

    function onConfirm() {
        postIfValid();
    }

    defineExpose({ onConfirm })

</script>

<template>
    <div class="row">
        <form class="offset-1 col-10" @submit.prevent="postIfValid">
            <InputForm type="text" :label="translations.task" v-model="task.task" id="task" />
        </form>
    </div>
</template>