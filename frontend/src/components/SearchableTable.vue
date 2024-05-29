<script setup>
    import { ref, watch, onBeforeUpdate } from "vue"
    import { useRoute } from "vue-router"
    import translations from '../config/nl-NL'


    const props = defineProps(['list', 'listType', 'singular'])

    const pageLength = 10; // number of items shown per page

    const validData = ref(props.list.filter((item) => item.active));
    const searchval = ref('');
    const page = ref(1);

    const updateList = () => {
        page.value = 1;
    }

    const excludeList = ['id', 'active', 'startTime', 'endTime', 'workSchedules']

    const filterList = () => {
        if (searchval.value.length == 0) {
            validData.value = props.list.filter((item) => item.active);
            console.log(validData.value)
            return;
        }

        validData.value = props.list.filter(item => {
            const search = Object.values(item).join('').toLowerCase();
            return search.includes(searchval.value) 
        })
    }

    onBeforeUpdate(filterList);

    const prevPage = () => {
        page.value = Math.max(page.value - 1, 1)
    }
    const nextPage = () => {
        page.value = page.value + 1
    }
</script>

<template>
    <div class="row justify-content-center py-4">
        <input class="form-control w-50 bg-light" v-model="searchval" :placeholder="translations.search + ' ' + props.singular.toLowerCase()" @keyup="updateList" />
    </div>
    <div class="d-flex justify-content-center align-items-center table-container">
        <button class="prev-button me-5" @click="prevPage" :disabled="page == 1"></button>

        <div v-if="validData.length == 0" class="text-center w-75">{{translations.no_results}}</div>
        <template v-else>
            <table class="table table-hover w-75 align-self-start">
                <thead>
                    <tr>
                        <th class="px-0" v-for="key in Object.keys(validData[0]).filter((key) => !excludeList.includes(key))">{{translations[key] ?? key}}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in validData.filter((key, value) => !excludeList.includes(key)).slice((page-1)*pageLength, page*pageLength)">
                        <td class="px-0" v-for="keyValuePair in Object.entries(item).filter(keyValuePair => !excludeList.includes(keyValuePair[0]))"><RouterLink :to="'/'+translations[props.listType]+'/'+item.id">{{keyValuePair[1]}}</RouterLink></td>
                    </tr>
                </tbody>
            </table>
        </template>

        <button class="next-button ms-5" @click="nextPage" :disabled="page >= Math.ceil(validData.length/pageLength)"></button>
    </div>
    <div class="my-5"></div>

</template>

<style scoped>
    .table-container {
        flex: 1 1 auto;
    }
</style>