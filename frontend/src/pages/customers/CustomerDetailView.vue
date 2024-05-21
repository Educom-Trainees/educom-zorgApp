<script setup>
    import { useMutation, useQuery, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getIndividual, putIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import { ref, toRaw, toRef, watch } from 'vue'
    import translations from '../../config/nl-NL'

    const CUSTOMERS = 'customers'

    var route = useRoute();
    const id = route.params.id;
    const queryClient = useQueryClient();
    const { isLoading, isError, data, error, isFetching, dataUpdatedAt } = useQuery({
        queryKey: [CUSTOMERS, id],
        queryFn: () => getIndividual(CUSTOMERS, id), //might want to move customers to route meta info
        placeholderData: () => {
            // Use the smaller/list version of the customer from the CUSTOMERS
            // query as the placeholder data for this customer query
            const placeholder = queryClient
                .getQueryData([CUSTOMERS])
                ?.find(cust => cust.id == id)
            return placeholder
        }
    })

    const customer = ref('')
    function updateCustomer(value) {
        customer.value = { ...value }
    }

    if (!isLoading.value && data.value) {
        updateCustomer(data.value)
    }
    watch(data, (value) => updateCustomer(value));

    const {isSuccess, mutate } = useMutation({
        mutationFn: putIndividual,
        onSuccess: (result) => {
            queryClient.invalidateQueries([CUSTOMERS, id])
            queryClient.cancelQueries([CUSTOMERS])
            const prevList = queryClient.getQueryData([CUSTOMERS])
            if (prevList) {
                const tempList = prevList.map(cust => cust.id == id ? customer.value : cust)
                queryClient.invalidateQueries([CUSTOMERS])
                queryClient.setQueryData([CUSTOMERS], tempList)
            }
        }
    })

    const postIfValid = () => {
        //console.log(firstName, lastName, address, postalCode, residence)
        mutate({ type: CUSTOMERS, id: id, body: JSON.stringify(customer.value) })
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
                <InputForm type="text" :label="translations.firstName" v-model="customer.firstName" id="firstName" />
                <InputForm type="text" :label="translations.lastName" v-model="customer.lastName" id="lastName" />
                <InputForm type="text" :label="translations.address" v-model="customer.address" id="address" />
                <div class="row">
                    <InputForm class="col-6" type="text" :label="translations.postalCode" v-model="customer.postalCode" id="postalCode" />
                    <InputForm class="col-6" type="text" :label="translations.residence" v-model="customer.residence" id="residence" />
                </div>
                <button type="submit" class="position-bottom-right default-button mb-4 me-4">{{translations.save}}</button>
            </form>
        </div>
    </template>
</template>