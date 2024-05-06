<script setup>
    import { ref, watch } from "vue"
    import { useRoute } from "vue-router"
    import translations from '../config/nl-NL';
    import isSubsequence from '../util/subsequence';


    const props = defineProps(['list', 'listType', 'page', 'changePage'])

    const pageLength = 10; // number of items shown per page

    const validData = ref(props.list);
    const searchval = ref('');


    const updateList = () => {
        props.changePage(1);
        console.log('search term:', searchval.value)
        const validItems = []
        props.list.forEach((item) => {
            const search = Object.values(item).join('').toLowerCase();
            if (isSubsequence(searchval.value, search)) {
                console.log('success', searchval.value, search)
                validItems.push(item);
            }
        })
        validData.value = validItems;
        console.log(validData.value)
    }

    watch(searchval, updateList)

    const prevPage = () => {
        props.changePage(Math.max(props.page - 1, 1))
    }
    const nextPage = () => {
        props.changePage(props.page + 1)
    }
</script>

<template>
    <div class="row justify-content-center mt-5">
        <input class="form-control w-50" v-model="searchval" :placeholder="translations.search + ' een ' + props.listType.toLowerCase()" @change="updateList" />
    </div>
    <div class="d-flex justify-content-center align-items-center mt-3">
        <button class="prev-button me-5" @click="prevPage" :disabled="props.page == 1"></button>

        <div v-if="validData.length == 0" class="text-center w-75">{{translations.no_results}}</div>
        <template v-else>
            <table class="table table-hover w-75">
                <thead>
                    <tr>
                        <th class="px-0" v-for="key in Object.keys(validData[0])">{{translations[key] ?? key}}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in validData.slice((props.page-1)*pageLength, props.page*pageLength)">
                        <td class="px-0" v-for="value in Object.values(item)"><RouterLink :to="'/'+props.listType+'/'+item.id">{{value}}</RouterLink></td>
                    </tr>
                </tbody>
            </table>
        </template>

        <button class="next-button ms-5" @click="nextPage" :disabled="props.page >= Math.ceil(validData.length/pageLength)"></button>
    </div>

</template>