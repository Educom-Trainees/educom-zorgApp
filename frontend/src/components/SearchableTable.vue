<script setup>
    import { ref, onBeforeUpdate } from "vue"
    import translations from '../config/nl-NL'

    //property definitions (could be explicitly defined)
    const props = defineProps(['list', 'listType', 'singular'])

    const pageLength = 10; // number of items shown per page, could be set based on viewport height

    const validData = ref(props.list.filter((item) => item.active)); //only show active items
    const searchval = ref('');
    const page = ref(1);

    /**
    * function called when list changes, resets page to 1
    */
    function updateList() {
        page.value = 1;
    }

    //list of items to be excluded when showing a list
    const excludeList = ['id', 'active', 'startTime', 'endTime', 'workSchedule']

    /**
    * function which calls the child function 'onConfirm' if it exists
    * then closes the modal
    */
    function filterList() {
        //reset to default if search field is empty
        if (searchval.value.length == 0) { 
            validData.value = props.list.filter((item) => item.active); //only show active items
            return;
        }

        //filters valid data based on search value
        validData.value = props.list.filter(item => {
            const search = Object.values(item).join('').toLowerCase(); //join values
            return search.includes(searchval.value) //check if search value exists in object
        })
    }

    onBeforeUpdate(filterList); //call filter function before updates

    /**
    * function to go to previous page
    * reduces page ref by 1 (down to a minimum of 1)
    */
    function prevPage() {
        page.value = Math.max(page.value - 1, 1)
    }

    /**
    * function to go to next page
    * increases page ref by 1
    */
    function nextPage() {
        page.value = page.value + 1
    }
</script>

<template>
    <div class="row justify-content-center py-4">
        <input class="form-control w-50 bg-light" v-model="searchval" :placeholder="translations.search + ' ' + props.singular.toLowerCase()" @keyup="updateList" />
    </div>
    <div class="d-flex justify-content-center align-items-center table-container">
        <button class="prev-button me-5" @click="prevPage" :disabled="page == 1"></button>

        <div v-if="validData.length == 0" class="text-center w-75 fs-4">{{translations.no_results}}</div>
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