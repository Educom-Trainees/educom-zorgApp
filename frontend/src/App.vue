<script setup>
    import { RouterLink, RouterView } from 'vue-router'
    import NavBar from './components/NavBar.vue'
    import HelloWorld from './components/HelloWorld.vue'
</script>

<template>
    <header>
        <NavBar v-if="!$route.meta.hideNav"></NavBar>
    </header>
    <div v-if="!$route.meta.hideNav" class="navbar-padding"></div>
    <RouterView />
</template>