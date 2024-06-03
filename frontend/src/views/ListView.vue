<script setup>
    import { ref, watch } from 'vue'
    import { useQuery } from 'vue-query';
    import { useRoute } from 'vue-router'
    import { getCollection } from '../api/collections'
    import AppModal from '../components/AppModal.vue'
    import SearchableTable from '../components/SearchableTable.vue'

    var route = useRoute();
    const listType = ref(route.meta.listType); //get list type from route meta info

    //when route changes update list type
    watch(() => route.meta.listType, (ntype, otype) => {
        console.log(ntype, otype)
        listType.value = ntype
    })

    //vue-query to GET list
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
            <SearchableTable :list="data" :listType="route.meta.listType" :singular="route.meta.singular" :key="listType + dataUpdatedAt" />
            <AppModal :title="route.meta.addButtonText" :buttonText="route.meta.addButtonText" buttonClass="position-bottom-right default-button mb-4 me-4" :bodyComponent="route.meta.newComponent" :accept="route.meta.addButtonText"/>
        </template>
    </main>
</template>