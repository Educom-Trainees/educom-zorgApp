<script setup>
    import { useMutation, useQuery, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getIndividual, putIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import { ref, toRaw, toRef, watch } from 'vue'
    import translations from '../../config/nl-NL'

    const EMPLOYEES = 'employees'

    var route = useRoute();
    const id = route.params.id;
    const queryClient = useQueryClient();
    const { isLoading, isError, data, error, isFetching, dataUpdatedAt } = useQuery({
        queryKey: [EMPLOYEES, id],
        queryFn: () => getIndividual(EMPLOYEES, id), //might want to move customers to route meta info
        placeholderData: () => {
            // Use the smaller/list version of the customer from the CUSTOMERS
            // query as the placeholder data for this customer query
            const placeholder = queryClient
                .getQueryData([EMPLOYEES])
                ?.find(e => e.id == id)
            return placeholder
        }
    })

    const employee = ref('')
    function updateEmployee(value) {
        employee.value = { ...value }
    }

    if (!isLoading.value && data.value) {
        updateEmployee(data.value)
    }
    watch(data, (value) => updateEmployee(value));

    const { isSuccess, mutate } = useMutation({
        mutationFn: putIndividual,
        onSuccess: (result) => {
            queryClient.invalidateQueries([EMPLOYEES, id])
            queryClient.cancelQueries([EMPLOYEES])
            const prevList = queryClient.getQueryData([EMPLOYEES])
            if (prevList) {
                const tempList = prevList.map(e => e.id == id ? employee.value : e)
                queryClient.invalidateQueries([EMPLOYEES])
                queryClient.setQueryData([EMPLOYEES], tempList)
            }
        }
    })




    const postIfValid = () => {
        //console.log(firstName, lastName, address, postalCode, residence)
        mutate({ type: EMPLOYEES, id: id, body: JSON.stringify(employee.value) })
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
                <InputForm type="text" :label="translations.firstName" v-model="employee.name" id="name" />
                <InputForm type="text" :label="translations.lastName" v-model="employee.lastName" id="lastName" />
                <InputForm type="text" :label="translations.userName" v-model="employee.userName" id="userName" />
                <InputForm type="text" :label="translations.address" v-model="employee.address" id="address" />
                <div class="row">
                    <InputForm class="col-6" type="text" :label="translations.postalCode" v-model="employee.postalcode" id="postalcode" />
                    <InputForm class="col-6" type="text" :label="translations.residence" v-model="employee.residence" id="residence" />
                </div>
                <InputForm type="text" label="placeholder for availability" id="temp" />
                <button type="submit" class="position-bottom-right default-button mb-4 me-4">{{translations.save}}</button>
            </form>
        </div>
    </template>
</template>