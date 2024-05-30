<script setup>
    import { useMutation, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { postIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import { ref } from 'vue'
    import translations from '../../config/nl-NL'

    const EMPLOYEES = 'employees'

    var route = useRoute();
    const id = route.params.id;

    const queryClient = useQueryClient();

    const employee = ref({
        id: 0,
        name: '',
        lastName: '',
        username: '',
        address: '',
        postalcode: '',
        residence: '',
        active: true,
        workSchedule: [],
    })

    const { isSuccess, mutate } = useMutation({
        mutationFn: postIndividual,
        onSuccess: (result) => {
            queryClient.invalidateQueries([EMPLOYEES])
        }
    })

    const postIfValid = () => {
        //console.log(firstName, lastName, address, postalCode, residence)
        mutate({ type: EMPLOYEES, body: JSON.stringify(employee.value) })
    }

    function onConfirm() {
        postIfValid();
    }

    defineExpose({ onConfirm })

</script>

<template>
    <div class="row">
        <form class="offset-1 col-10" @submit.prevent="postIfValid">
            <InputForm type="text" :label="translations.firstName" v-model="employee.name" id="name" />
            <InputForm type="text" :label="translations.lastName" v-model="employee.lastName" id="lastName" />
            <InputForm type="text" :label="translations.userName" v-model="employee.username" id="username" />
            <InputForm type="text" :label="translations.address" v-model="employee.address" id="address" />
            <div class="row">
                <InputForm class="col-12 col-md-6" type="text" :label="translations.postalCode" v-model="employee.postalcode" id="postalcode" />
                <InputForm class="col-12 col-md-6" type="text" :label="translations.residence" v-model="employee.residence" id="residence" />
            </div>
        </form>
    </div>
</template>