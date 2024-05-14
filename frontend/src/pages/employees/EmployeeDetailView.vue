<script setup>
    import { useMutation, useQuery } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import { ref, toRaw, toRef, watch } from 'vue'
    import translations from '../../config/nl-NL'
    import { putEmployee } from '../../api/employees'

    var route = useRoute();
    const id = route.params.id;

    const { isLoading, isError, data, error, isFetching, dataUpdatedAt } = useQuery({
        queryKey: ['employees', id],
        queryFn: () => getIndividual('employees', id), //might want to move customers to route meta info
    })

    const employee = ref(JSON.parse(JSON.stringify(data?.value ? data.value : '')))
    watch(data, (value) => {
        console.log(value)
        employee.value = JSON.parse(JSON.stringify(value))
    });

    const {isSuccess, mutate } = useMutation({
        mutationFn: putEmployee,
    })

    const postIfValid = () => {
        //console.log(firstName, lastName, address, postalCode, residence)
        mutate({ id: id, employee: JSON.stringify(employee.value) })
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
                <InputForm type="text" :label="translations.firstName" v-model="employee.firstName" id="firstName" />
                <InputForm type="text" :label="translations.lastName" v-model="employee.lastName" id="lastName" />
                <InputForm type="text" :label="translations.userName" v-model="employee.userName" id="userName" />
                <InputForm type="text" :label="translations.address" v-model="employee.address" id="address" />
                <div class="row">
                    <InputForm class="col-6" type="text" :label="translations.postalCode" v-model="employee.postalCode" id="postalCode" />
                    <InputForm class="col-6" type="text" :label="translations.residence" v-model="employee.residence" id="residence" />
                </div>
                <InputForm type="text" label="placeholder for availability" id="temp" />
                <button type="submit" class="position-bottom-right default-button mb-4 me-4">{{translations.save}}</button>
            </form>
        </div>
    </template>
</template>