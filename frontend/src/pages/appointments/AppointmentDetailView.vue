<script setup>
    import { computed, ref, watch } from 'vue'
    import { useMutation, useQuery, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getCollection, getIndividual, putIndividual } from '../../api/collections'
    import { parseTime, stringifyTime } from '../../utils/Time'
    import InputForm from '../../components/InputForm.vue'
    import CustomerSelector from './CustomerSelector.vue'
    import EmployeeSelector from './EmployeeSelector.vue'
    import TaskList from '../../components/TaskList.vue'
    import AppointmentErrors from './AppointmentErrors.vue'
    import AppModal from '../../components/AppModal.vue'
    import translations from '../../config/nl-NL'

    //magic values set here
    const APPOINTMENTS = 'appointments'
    const CUSTOMERS = 'customers'
    const EMPLOYEES = 'employees'

    var route = useRoute();
    const id = route.params.id; //get id from route params
    const queryClient = useQueryClient();

    //vue-query to GET appointment by id
    const { isLoading: appointmentLoading, data: appointmentData } = useQuery({
        queryKey: [APPOINTMENTS, id],
        queryFn: () => getIndividual(APPOINTMENTS, id),
        placeholderData: () => {
            const placeholder = queryClient
                .getQueryData([APPOINTMENTS])
                ?.find(apt => apt.id == id)
            return placeholder
        }
    })

    //vue-query to GET customers
    const { isLoading: customersLoading, data: customersData} = useQuery({
        queryKey: [CUSTOMERS],
        queryFn: () => getCollection(CUSTOMERS),
        placeholderData: () => {
            const placeholder = queryClient
                .getQueryData([CUSTOMERS])
            return placeholder ?? []
        }
    })

    //vue-query to GET employees
    const { isLoading: employeesLoading, data: employeesData } = useQuery({
        queryKey: [EMPLOYEES],
        queryFn: () => getCollection(EMPLOYEES),
        placeholderData: () => {
            const placeholder = queryClient
                .getQueryData([EMPLOYEES])
            return placeholder ?? [];
        }
    })

    //refs to store data from queries
    const appointment = ref('')
    const customers = ref([])
    const employees = ref([])

    //variables to track changes
    const customerRef = ref(0)
    const employeeRef = ref(0)
    const appointmentTasksRef = ref([])
    const dateRef = ref(new Date())

    /**
    * function to update appointment ref
    * @param {Object} value appointment from vue-query data 
    */
    function updateAppointment(value) {
        appointment.value = JSON.parse(JSON.stringify(value)) //stringifying and then parsing an object is a way of creating a deep copy
        customerRef.value = appointment.value.customerId
        employeeRef.value = appointment.value.employeeId

        //convert times to correct format for vue-datepicker
        var atr = appointment.value.appointmentTasks
        atr = atr.map((task) => {
            task.startTime = parseTime(task.startTime)
            task.endTime = parseTime(task.endTime)
            return task
        })
        appointmentTasksRef.value = atr

        var d = new Date(appointment.value.date)
        d.setMinutes(d.getMinutes() + d.getTimezoneOffset()) //remove timezone offset to avoid days being off by one
        dateRef.value = d
    }

    /**
    * function to update customers ref
    * @param {Array} value list of customers from vue-query data 
    */
    function updateCustomers(value) {
        customers.value = [...value]
    }

    /**
    * function to update employees ref
    * @param {Array} value list of employees from vue-query data 
    */
    function updateEmployees(value) {
        employees.value = [ ...value ]
    }

    //if data had already been fetched sets refs
    if (!appointmentLoading.value && appointmentData.value) {
        updateAppointment(appointmentData.value)
    }
    if (!customersLoading.value && customersData.value) {
        updateCustomers(customersData.value)
    }
    if (!employeesLoading.value && employeesData.value) {
        updateEmployees(employeesData.value)
    }

    //when data changes update refs
    watch(appointmentData, (value) => updateAppointment(value));
    watch(customersData, (value) => updateCustomers(value));
    watch(employeesData, (value) => updateEmployees(value));

    //vue-query to PUT appointment
    const {isSuccess, mutate } = useMutation({
        mutationFn: putIndividual,
        onSuccess: (result) => { //fixes cached data when update is successful
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

    /**
    * function to convert times back to string format
    * @param {Array} tasks
    * @returns {Array} array with updated time values
    */
    function fixTimes(tasks) {
        return tasks.map((task) => {
            console.log(task)
            task.startTime = stringifyTime(task.startTime)
            task.endTime = stringifyTime(task.endTime)
            return task
        })
    }

    /**
    * function to send PUT request (error checking not implemented yet)
    */
    function putIfValid() {
        var postAppointment = JSON.parse(JSON.stringify(appointment.value))
        postAppointment.date = dateRef.value.toISOString().split('T')[0]
        postAppointment.employeeId = employeeRef.value
        postAppointment.appointmentTasks = fixTimes(appointment.value.appointmentTasks)
        mutate({ type: APPOINTMENTS, id: id, body: JSON.stringify(postAppointment) })
        updateAppointment(postAppointment)
    }

    //errors shown in pop-up before sending the updated appointment (not implemented)
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
                <div class="col-12 col-sm-6 col-lg-3">
                    <CustomerSelector id="customerSelect" :options="customers" v-model="customerRef" />
                </div>
                <div class="col-12 col-sm-6 offset-lg-1 col-lg-3">
                    <EmployeeSelector id="employeeSelect" :options="employees" :signInTime="appointment.registerTime" :signOutTime="appointment.logoutTime" v-model="employeeRef" />
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12 offset-lg-1 col-lg-10">
                    <TaskList v-model="appointment.appointmentTasks" :defaultOptions="appointment.customerTasks"/>
                </div>
            </div>
            <AppModal :title="translations.confirmation" :buttonText="translations.save" buttonClass="position-bottom-right default-button border-white mb-4 me-4" :bodyComponent="AppointmentErrors" :properties="{errors: errors, onConfirm: putIfValid}" :accept="translations.save" />

        </div>
    </template>
</template>