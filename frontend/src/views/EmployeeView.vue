<script setup>
    import { ref, watch } from "vue"
    import translations from '../config/nl-NL';
    import isSubsequence from '../util/subsequence';
    import CustomerItem from '../components/CustomerItem.vue'

    const props = defineProps(['list'])

    const validData = ref([{ initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: 'done' },
        { initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: 'done' },
        { initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: 'done' },
        { initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: 'in_progress' },
        { initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: 'done' },
        { initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: '' },
        { initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: '' },
        { initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: '' },
        { initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: '' },
        { initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: '' },
        { initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: '' },
        { initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: '' },
        { initials: "PP", name: "Pietje Precies", address: "Wegenweg 12, 3456GH Pietjesburg", task_progress: '' }]);
    const searchval = ref('');

    const updateList = () => {
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
        //.slice((page-1)*10, page*10)
    }

    watch(searchval, updateList)
</script>

<template>
    <div class="row mt-3">
        <div class="offset-1 col-10 offset-md-3 col-md-6">

        <input class="form-control" v-model="searchval" @change="updateList" />
        </div>
    </div>

    <div class="row">
        <div class="col offset-md-2 col-md-8">
            <hr class="mb-0"/>
            <div class="customer-list pb-1 pt-1">
                <template v-for="item in validData">
                    <CustomerItem :initials="item.initials" :name="item.name" :address="item.address" :taskprogress="item.task_progress"/>
                </template>
            </div>
            <hr class="mt-0"/>
            <button class="float-end default-button mt-3 me-1">Volgende dag</button>
        </div>
    </div>
</template>