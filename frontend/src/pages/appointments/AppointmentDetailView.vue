<script setup>
    import { useMutation, useQuery, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getCollection, getIndividual, putIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import { ref, watch } from 'vue'
    import translations from '../../config/nl-NL'

    const APPOINTMENTS = 'appointments'
    const CUSTOMERS = 'customers'
    const EMPLOYEES = 'employees'

    var route = useRoute();
    const id = route.params.id;
    const queryClient = useQueryClient();
    const { isLoading: appointmentLoading, data: appointmentData} = useQuery({
        queryKey: [APPOINTMENTS, id],
        queryFn: () => getIndividual(APPOINTMENTS, id), //might want to move customers to route meta info
        placeholderData: () => {
            // Use the smaller/list version of the customer from the CUSTOMERS
            // query as the placeholder data for this customer query
            const placeholder = queryClient
                .getQueryData([APPOINTMENTS])
                ?.find(apt => apt.id == id)
            return placeholder
        }
    })
    const { isLoading: customersLoading, data: customersData} = useQuery({
        queryKey: [CUSTOMERS],
        queryFn: () => getCollection(CUSTOMERS), //might want to move customers to route meta info
        placeholderData: () => {
            // Use the smaller/list version of the customer from the CUSTOMERS
            // query as the placeholder data for this customer query
            const placeholder = queryClient
                .getQueryData([CUSTOMERS])
            return placeholder
        }
    })
    const { isLoading: employeesLoading, data: employeesData } = useQuery({
        queryKey: [EMPLOYEES],
        queryFn: () => getCollection(EMPLOYEES), //might want to move customers to route meta info
        placeholderData: () => {
            // Use the smaller/list version of the customer from the CUSTOMERS
            // query as the placeholder data for this customer query
            const placeholder = queryClient
                .getQueryData([EMPLOYEES])
            return placeholder
        }
    })

    const appointment = ref('')
    const customers = ref([])
    const employees = ref([])

    function updateAppointment(value) {
        appointment.value = { ...value }
    }
    function updateCustomers(value) {
        customers.value = { ...value }
    }
    function updateEmployees(value) {
        employees.value = { ...value }
    }

    if (!appointmentLoading.value && appointmentData.value) {
        updateAppointment(appointmentData.value)
    }
    if (!customersLoading.value && customersData.value) {
        updateCustomers(customersData.value)
    }
    if (!employeesLoading.value && employeesData.value) {
        updateEmployees(employeesData.value)
    }

    watch(appointmentData, (value) => updateAppointment(value));
    watch(customersData, (value) => updateCustomers(value));
    watch(employeesData, (value) => updateEmployees(value));


    const {isSuccess, mutate } = useMutation({
        mutationFn: putIndividual,
        onSuccess: (result) => {
            queryClient.invalidateQueries([APPOINTMENTS, id])
            queryClient.cancelQueries([APPOINTMENTS])
            const prevList = queryClient.getQueryData([APPOINTMENTS])
            if (prevList) {
                const tempList = prevList.map(apt => apt.id == id ? appointment.value : apt)
                queryClient.invalidateQueries([APPOINTMENTS])
                queryClient.setQueryData([APPOINTMENTS], tempList)
            }
        }
    })

    const postIfValid = () => {
        //console.log(firstName, lastName, address, postalCode, residence)
        mutate({ type: APPOINTMENTS, id: id, body: JSON.stringify(appointment.value) })
    }

    const isLoading = [appointmentLoading, customersLoading, employeesLoading].some(ref => !!ref.value);
</script>

<template>
    <template v-if="isLoading && !data">
        <div>Loading...</div>
    </template>
    <template v-else>
        <div class="row">
            <form class="offset-1 col-10" @submit.prevent="postIfValid">
                <InputForm type="text" :label="translations.firstName" v-model="appointment.firstName" id="firstName" />
                <InputForm type="text" :label="translations.lastName" v-model="appointment.lastName" id="lastName" />
                <InputForm type="text" :label="translations.address" v-model="appointment.address" id="address" />
                <div class="row">
                    <InputForm class="col-6" type="text" :label="translations.postalCode" v-model="appointment.postalCode" id="postalCode" />
                    <InputForm class="col-6" type="text" :label="translations.residence" v-model="appointment.residence" id="residence" />
                </div>
                <button type="submit" class="position-bottom-right default-button mb-4 me-4">{{translations.save}}</button>
            </form>
        </div>
    </template>
</template>