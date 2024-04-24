<script setup>
    import Table from '../components/Table.vue'
</script>

<template>
    <main>
        <div class="d-flex justify-content-center mt-3">
            <Table />
        </div>
        <button class="position-bottom-right default-button mb-4 me-4">{{$route.meta.addButtonText}}</button>
    </main>
</template>