<script setup>
    import { ref } from 'vue'
    import { useQuery } from 'vue-query';
    import Table from '../components/Table.vue'
    import { useRoute } from 'vue-router'
    import { getEmployees } from '../api/employees'
    import { getCustomers } from '../api/customers';
    import { getTasks } from '../api/tasks';

    var route = useRoute();
    var listFunction = () => { [] };
    const page = ref(1);
    switch (route.meta.listType) {
        case 'employees':
            listFunction = () => getEmployees(page);
            break;
        case 'customers':
            listFunction = () => getCustomers(page);
            break;
        case 'tasks':
            listFunction = () => getTasks(page);
            break;
        default:
            console.log('error');
            break;
    }

    const { isLoading, isError, data, error, isFetching, isPreviousData } = useQuery({
        queryKey: [route.meta.listType, page],
        queryFn: listFunction,
        keepPreviousData: true,
    })


    const prevPage = () => {
        page.value = Math.max(page.value - 1, 1)
    }
    const nextPage = () => {
        if (!isPreviousData.value) {
            page.value = page.value + 1
        }
    }

</script>

<template>
    <main>
        <template v-if="isLoading && !data">
            <div>Loading...</div>
        </template>
        <template v-else>
            <div class="d-flex justify-content-center align-items-center mt-3">
                <button class="prev-button me-3" @click="prevPage" :disabled="page == 1"></button>
                <Table :list="data" />
                <button class="next-button ms-3" @click="nextPage"></button>
            </div>
            <button class="position-bottom-right default-button mb-4 me-4">{{$route.meta.addButtonText}}</button>
        </template>
    </main>
</template>