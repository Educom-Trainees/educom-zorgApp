<script setup>
    import { ref, watch } from "vue"
    import { useRoute } from "vue-router"

    //var route = useRoute()
    //watch(() => route.params.type, (newType, oldType) => {
    //    // react to route changes...
    //})

    const dummy_list = ref([
        { name: 'item 1', email: 'email@email.com' },
        { name: 'item 2', email: 'email@email.com' },
        { name: 'item 3', email: 'email@email.com' }
    ]);
</script>

<template>
    <table class="table table-hover w-75">
        <thead>
            <tr>
                <th v-for="key in Object.keys(dummy_list[0])">{{key}}</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="item in dummy_list">
                <td class="px-0" v-for="value in Object.values(item)"><RouterLink to="/login">{{value}}</RouterLink></td>
            </tr>
        </tbody>
    </table>
</template>