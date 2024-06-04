<script setup>
    import { RouterLink, RouterView } from 'vue-router'
    import NavBar from './components/NavBar.vue'
</script>

<template>
    <header>
        <NavBar v-if="!$route.meta.hideNav"></NavBar>
    </header>
    <RouterView />
</template>