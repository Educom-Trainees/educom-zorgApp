<script setup>
    import { ref } from 'vue'
    import { useMutation, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { postIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import translations from '../../config/nl-NL'

    const CUSTOMERS = 'customers'

    var route = useRoute();
    const id = route.params.id; //get id from route params

    const queryClient = useQueryClient();

    //ref for new customer
    const customer = ref({
        id: 0,
        name: '',
        lastName: '',
        address: '',
        postalcode: '',
        residence: '',
        active: true,
    })

    //vue-query to POST customer
    const { isSuccess, mutate } = useMutation({
        mutationFn: postIndividual,
        onSuccess: (result) => {
            queryClient.invalidateQueries([CUSTOMERS])
        }
    })

    /**
    * function to send POST request (error checking not implemented yet)
    */
    function postIfValid() {
        mutate({ type: CUSTOMERS, body: JSON.stringify(customer.value) })
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
            <InputForm type="text" :label="translations.firstName" v-model="customer.name" id="name" />
            <InputForm type="text" :label="translations.lastName" v-model="customer.lastName" id="lastName" />
            <InputForm type="text" :label="translations.address" v-model="customer.address" id="address" />
            <div class="row">
                <InputForm class="col-12 col-md-6" type="text" :label="translations.postalCode" v-model="customer.postalcode" id="postalcode" />
                <InputForm class="col-12 col-md-6" type="text" :label="translations.residence" v-model="customer.residence" id="residence" />
            </div>
        </form>
    </div>
</template>