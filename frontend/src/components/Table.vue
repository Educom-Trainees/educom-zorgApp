<script setup>
    import { ref, watch } from "vue"
    import { useRoute } from "vue-router"
    const props = defineProps(['list'])
    console.log(props.list)
</script>

<template>
    <table class="table table-hover w-75">
        <thead>
            <tr>
                <th v-for="key in Object.keys(props.list[0])">{{key}}</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="item in props.list">
                <td class="px-0" v-for="value in Object.values(item)"><RouterLink to="/login">{{value}}</RouterLink></td>
            </tr>
        </tbody>
    </table>
</template>