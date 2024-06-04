<script setup>
    import { h, ref, watch } from 'vue'
    import { useMutation, useQuery, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getIndividual, putIndividual } from '../../api/collections'
    import { parseTime, stringifyTime } from '../../utils/Time'
    import InputForm from '../../components/InputForm.vue'
    import TaskList from '../../components/TaskList.vue'
    import translations from '../../config/nl-NL'

    const CUSTOMERS = 'customers'

    var route = useRoute();
    const id = route.params.id; //get id from route params
    const queryClient = useQueryClient();

    //vue-query to GET customer by id
    const { isLoading, isError, data, error, isFetching, dataUpdatedAt } = useQuery({
        queryKey: [CUSTOMERS, id],
        queryFn: () => getIndividual(CUSTOMERS, id),
        placeholderData: () => {
            const placeholder = queryClient
                .getQueryData([CUSTOMERS])
                ?.find(cust => cust.id == id)
            return placeholder
        }
    })

    //refs to store data from queries
    const customer = ref('')
    const tasksRef = ref([])

    /**
    * function to update refs
    * @param {Object} value customer from vue-query data 
    */
    function updateCustomer(value) {
        customer.value = { ...value }

        var tasks = JSON.parse(JSON.stringify(value?.tasks ?? []))
        tasks = tasks.map((task) => {
            task.startTime = parseTime(task.startTime)
            task.endTime = parseTime(task.endTime)
            return task
        })
        tasksRef.value = tasks
    }

    //if data had already been fetched sets refs
    if (!isLoading.value && data.value) {
        updateCustomer(data.value)
    }

    //when data changes update refs
    watch(data, (value) => updateCustomer(value));

    //vue-query to PUT customer
    const {isSuccess, mutate } = useMutation({
        mutationFn: putIndividual,
        onSuccess: (result) => { //fixes cached data when update is successful
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

    //ref to toggle popup visibility
    const showPopup = ref(false)

    /**
    * function to send PUT request (error checking not implemented yet)
    */
    function putIfValid() {
        var postCustomer = JSON.parse(JSON.stringify(customer.value))
        postCustomer.tasks = fixTimes(tasksRef.value)
        mutate({ type: CUSTOMERS, id: id, body: JSON.stringify(postCustomer) })
        updateCustomer(postCustomer)

        //show confirmation
        showPopup.value = true
        setTimeout(() => showPopup.value = false, 2500);
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
            <div class="offset-1 col-10">
                <InputForm type="text" :label="translations.firstName" v-model="customer.name" id="name" />
                <InputForm type="text" :label="translations.lastName" v-model="customer.lastName" id="lastName" />
                <InputForm type="text" :label="translations.address" v-model="customer.address" id="address" />
                <div class="row">
                    <InputForm class="col-6" type="text" :label="translations.postalCode" v-model="customer.postalcode" id="postalcode" />
                    <InputForm class="col-6" type="text" :label="translations.residence" v-model="customer.residence" id="residence" />
                </div>
                <div class="col-12">
                    <TaskList v-model="tasksRef" :defaultOptions="[]" />
                </div>
                <div class="position-bottom-right mb-4 me-4">
                    <button type="button" :class="'toggle-button me-2' + (customer.active ? ' active' : '')" data-bs-toggle="button" aria-pressed="true" @click="() => customer.active = !customer.active">{{customer.active ? translations.deactivate : translations.activate}}</button>
                    <button type="submit" class="default-button" @click="putIfValid">{{translations.save}}</button>
                </div>
            </div>
        </div>

        <div id="popup" :class="showPopup ? 'saved-pop-up' : 'invisible'">{{translations.customer + ' ' + translations.saved + '!'}}</div>
    </template>
</template>
