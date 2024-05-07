<script setup>
    import { ref, toRaw, watch } from 'vue'
    import { useQuery } from 'vue-query';
    import SearchableTable from '../components/SearchableTable.vue'
    import { useRoute } from 'vue-router'
    import { getEmployees } from '../api/employees'
    import { getCustomers } from '../api/customers';
    import { getTasks } from '../api/tasks';

    var route = useRoute();
    const listType = ref(route.meta.listType);
    const page = ref(1);

    const changePage = (newValue) => { page.value = newValue }

    watch(() => route.meta.listType, (ntype, otype) => {
        console.log(ntype, otype)
        listType.value = ntype
        page.value = 1;
    })

    const fetchData = () => {
        console.log("fetching ", listType.value)
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

    const { isLoading, isError, data, error, isFetching, dataUpdatedAt } = useQuery({
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
            <SearchableTable :list="toRaw(data)" :listType="route.meta.singular" :page ="page" :changePage="changePage" :key="listType + dataUpdatedAt"/> 
            <button class="position-bottom-right default-button mb-4 me-4">{{route.meta.addButtonText}}</button>
        </template>
    </main>
</template>