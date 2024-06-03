<script setup>
    import { useMutation, useQuery, useQueryClient } from 'vue-query'
    import { useRoute } from 'vue-router'
    import { getIndividual, putIndividual } from '../../api/collections'
    import InputForm from '../../components/InputForm.vue'
    import TaskList from '../../components/TaskList.vue'
    import { ref, toRaw, toRef, watch } from 'vue'
    import translations from '../../config/nl-NL'
    import { parseTime, stringifyTime } from '../../utils/Time'

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
    const tasksRef = ref([])
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

    function fixTimes(tasks) {
        return tasks.map((task) => {
            console.log(task)
            task.startTime = stringifyTime(task.startTime)
            task.endTime = stringifyTime(task.endTime)
            return task
        })
    }

    const postIfValid = () => {
        var postCustomer = JSON.parse(JSON.stringify(customer.value))
        postCustomer.tasks = fixTimes(tasksRef.value)
        console.log(JSON.stringify(postCustomer))
        //console.log(firstName, lastName, address, postalCode, residence)
        mutate({ type: CUSTOMERS, id: id, body: JSON.stringify(postCustomer) })
        updateCustomer(postCustomer)
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
                    <button type="submit" class="default-button" @click="postIfValid">{{translations.save}}</button>
                </div>
            </div>
        </div>
    </template>
</template>