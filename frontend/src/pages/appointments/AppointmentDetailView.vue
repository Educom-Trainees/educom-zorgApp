<script setup>
    import { useMutation, useQuery, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getCollection, getIndividual, putIndividual } from '../../api/collections'
    import { parseTime, stringifyTime } from '../../utils/Time';
    import InputForm from '../../components/InputForm.vue'
    import CustomerSelector from './CustomerSelector.vue'
    import EmployeeSelector from './EmployeeSelector.vue'
    import TaskList from '../../components/TaskList.vue'
    import AppointmentErrors from './AppointmentErrors.vue'
    import AppModal from '../../components/AppModal.vue'

    import { computed, ref, watch } from 'vue'
    import translations from '../../config/nl-NL'

    const APPOINTMENTS = 'appointments'
    const CUSTOMERS = 'customers'
    const EMPLOYEES = 'employees'

    var route = useRoute();
    const id = route.params.id;
    const queryClient = useQueryClient();
    const { isLoading: appointmentLoading, data: appointmentData} = useQuery({
        queryKey: [APPOINTMENTS, id],
        queryFn: () => getIndividual(APPOINTMENTS, id),//getIndividual(APPOINTMENTS, id), //might want to move customers to route meta info
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
            return placeholder ?? []
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
            return placeholder ?? [];
        }
    })

    const appointment = ref('')
    const customers = ref([])
    const employees = ref([])

    //variables to track changes
    const customerRef = ref(0)
    const employeeRef = ref(0)
    const appointmentTasksRef = ref([])
    const dateRef = ref(new Date())

    function updateAppointment(value) {
        appointment.value = JSON.parse(JSON.stringify(value))
        customerRef.value = appointment.value.customerId
        employeeRef.value = appointment.value.employeeId

        var atr = appointment.value.appointmentTasks
        atr = atr.map((task) => {
            task.startTime = parseTime(task.startTime)
            task.endTime = parseTime(task.endTime)
            return task
        })
        appointmentTasksRef.value = atr

        var d = new Date(appointment.value.date)
        d.setMinutes(d.getMinutes() + d.getTimezoneOffset())
        dateRef.value = d
    }
    function updateCustomers(value) {
        customers.value = [...value]
    }
    function updateEmployees(value) {
        employees.value = [ ...value ]
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

    function fixTimes(tasks) {
        return tasks.map((task) => {
            console.log(task)
            task.startTime = stringifyTime(task.startTime)
            task.endTime = stringifyTime(task.endTime)
            return task
        })
    }

    const postIfValid = () => {
        appointment.value.date = dateRef.value.toISOString().split('T')[0]
        appointment.value.employeeId = employeeRef.value
        appointment.value.appointmentTasks = fixTimes(appointment.value.appointmentTasks)
        console.log(JSON.stringify(appointment.value))
        mutate({ type: APPOINTMENTS, id: id, body: JSON.stringify(appointment.value) })
    }

    const errors = computed(() => {
        const errors = []
        if (false) {
            errors.push({ message: 'Too many tasks planned in a short timeframe' })
        }
        if (false) {
            errors.push({ message: 'Very long appointment time with very few tasks' })
        }
        if (false) {
            errors.push({ message: 'appointment time longer than employee\'s remaining working hours' })
        }
        return errors
    })

    const isLoading = [appointmentLoading, customersLoading, employeesLoading].some(ref => !!ref.value);
    const data = [appointmentData, customersData, employeesData].every(ref => !!ref);
</script>

<template>
    <template v-if="isLoading && !data">
        <div>Loading...</div>
    </template>
    <template v-else>
        <div class="mt-3 mx-2">
            <div class="row">
                <div class="col-12 offset-lg-1 col-lg-3 p-0">
                    <VueDatePicker v-model="dateRef" inline auto-apply no-today locale="nl" :enable-time-picker="false" week-start="0" month-name-format="long" />
                </div>
                <div class="col-12 col-lg-3">
                    <CustomerSelector id="customerSelect" :options="customers" v-model="customerRef" />
                </div>
                <div class="col-12 offset-lg-1 col-lg-3">
                    <EmployeeSelector id="employeeSelect" :options="employees" :signInTime="appointment.registerTime" :signOutTime="appointment.logoutTime" v-model="employeeRef" />
                </div>
            </div>
            <div class="row">
                <div class="col-12 offset-lg-1 col-lg-10">
                    <TaskList v-model="appointment.appointmentTasks" :defaultOptions="appointment.customerTasks" />
                </div>
            </div>
            <AppModal :title="translations.confirmation" :buttonText="translations.save" buttonClass="position-bottom-right default-button mb-4 me-4" :bodyComponent="AppointmentErrors" :properties="{errors: errors, onConfirm: postIfValid}" :accept="translations.save" />

        </div>
    </template>
</template>