<script setup>
    import { ref, watch } from "vue"
    import translations from '../config/nl-NL';
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
        // TODO convert to .filter()
        const validItems = []
        props.list.forEach((item) => {
            const search = Object.values(item).join('').toLowerCase();
            if (search.includes(searchval.value)) {
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
    <div class="row justify-content-center mt-5">
        <input class="form-control w-50" v-model="searchval" @change="updateList" />
    </div>

    <div class="row justify-content-center">
        <div class="w-75">
            <hr class="mb-0"/>
            <div class="customer-list pb-1 pt-1">
                <template v-for="item in validData">
                    <CustomerItem :initials="item.initials" :name="item.name" :address="item.address" :taskprogress="item.task_progress"/>
                </template>
            </div>
            <hr class="mt-0"/>
            <button class="position-bottom-right default-button mb-4 me-4">Next</button>
        </div>
    </div>
</template>