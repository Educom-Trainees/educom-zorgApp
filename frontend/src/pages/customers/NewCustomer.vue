<script setup>
    import { useMutation, useQuery, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import { ref, toRaw, toRef, watch } from 'vue'
    import translations from '../../config/nl-NL'
    import { postCustomer } from '../../api/customers'

    const CUSTOMERS = 'customers'

    var route = useRoute();
    const id = route.params.id;

    const queryClient = useQueryClient();

    const customer = ref({
        id: 0,
        firstName: '',
        lastName: '',
        address: '',
        postalCode: '',
        residence: '',
        active: true,
    })

    const { isSuccess, mutate } = useMutation({
        mutationFn: postCustomer,
        onSuccess: (result) => {
            queryClient.invalidateQueries([CUSTOMERS])
        }
    })

    const postIfValid = () => {
        //console.log(firstName, lastName, address, postalCode, residence)
        mutate(JSON.stringify(customer.value))
    }

    function onConfirm() {
        postIfValid();
    }

    defineExpose({ onConfirm })

</script>

<template>
    <div class="row">
        <form class="offset-1 col-10" @submit.prevent="postIfValid">
            <InputForm type="text" :label="translations.firstName" v-model="customer.firstName" id="firstName" />
            <InputForm type="text" :label="translations.lastName" v-model="customer.lastName" id="lastName" />
            <InputForm type="text" :label="translations.address" v-model="customer.address" id="address" />
            <div class="row">
                <InputForm class="col-12 col-md-6" type="text" :label="translations.postalCode" v-model="customer.postalCode" id="postalCode" />
                <InputForm class="col-12 col-md-6" type="text" :label="translations.residence" v-model="customer.residence" id="residence" />
            </div>
        </form>
    </div>
</template>