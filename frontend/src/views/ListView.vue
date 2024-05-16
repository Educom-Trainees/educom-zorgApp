<script setup>
    import { ref, toRaw, watch } from 'vue'
    import { useQuery } from 'vue-query';
    import SearchableTable from '../components/SearchableTable.vue'
    import AppModal from '../components/AppModal.vue'
    import { useRoute } from 'vue-router'
    import { getCollection } from '../api/collections'


    var route = useRoute();
    const listType = ref(route.meta.listType);

    watch(() => route.meta.listType, (ntype, otype) => {
        console.log(ntype, otype)
        listType.value = ntype
    })

    const { isLoading, isError, data, error, isFetching, dataUpdatedAt } = useQuery({
        queryKey: [listType],
        queryFn: () => getCollection(listType.value),
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
            <SearchableTable :list="toRaw(data)" :listType="route.meta.listType" :singular="route.meta.singular" :key="listType + dataUpdatedAt" />
            <AppModal :title="route.meta.addButtonText" :buttonText="route.meta.addButtonText" buttonClass="position-bottom-right default-button mb-4 me-4" :bodyComponent="route.meta.newComponent"/>
        </template>
    </main>
</template>