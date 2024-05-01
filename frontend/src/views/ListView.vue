<script setup>
    import { ref, toRaw, watch } from 'vue'
    import { useQuery, useQueryClient } from 'vue-query';
    import Table from '../components/Table.vue'
    import { useRoute } from 'vue-router'
    import { getEmployees } from '../api/employees'
    import { getCustomers } from '../api/customers';
    import { getTasks } from '../api/tasks';

    var route = useRoute();
    const listType = ref(route.meta.listType);
    const qClient = useQueryClient()

    watch(() => route.meta.listType, (ntype, otype) => {
        qClient.clear();
        console.log(ntype, otype)
        listType.value = ntype
    })

    const fetchData = () => {
        switch (listType.value) {
            case 'employees':
                return getEmployees();
            case 'customers':
                return getCustomers();
            case 'tasks':
                return getTasks();
            default:
                console.log('error');
                break;
        }
    };

    const { isLoading, isError, data, error, isFetching } = useQuery({
        queryKey: ['list', listType],
        queryFn: fetchData,
    })

</script>

<template>
    <main>
        <template v-if="isLoading && !data">
            <div>Loading...</div>
        </template>
        <template v-else-if="isError || !data">
            <div>Something went wrong</div>
        </template>
        <template v-else class="h-100">
            <Table :list="toRaw(data)" :listType="route.meta.singular"/> 
            <button class="position-bottom-right default-button mb-4 me-4">{{route.meta.addButtonText}}</button>
        </template>
    </main>
</template>