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
    <div class="full-page-container">

        <div class="row customer-list">
            <div class="searchbar-height">
                <div class="row mt-3 mb-0">
                    <div class="offset-1 col-10 offset-md-3 col-md-6">
                        <div class="input-group">
                            <span class="input-group-prepend">
                                <button class="btn border-0 rounded-pill me-n5" type="button">
                                    <i class="bi bi-search"></i>
                                </button>
                            </span>
                            <input class="form-control border rounded-pill bg-light" v-model="searchval" @change="updateList">
                        </div>
                    </div>
                </div>
                <div class="col-12 offset-md-2 col-md-8 mt-3">
                    <div class="ms-1">{{new Date().toLocaleDateString('nl-NL', {day: 'numeric', month: 'long'})}}</div>
                    <hr class="mb-0 mt-0" />
                </div>
            </div>
            <div class="col offset-md-2 col-md-8">
                <div class="pb-1 pt-1">
                    <template v-for="item in validData">
                        <CustomerItem :initials="item.initials" :name="item.name" :address="item.address" :taskprogress="item.task_progress" />
                    </template>
                </div>
            </div>
        </div>
        <div class="footer-height">
            <div class="col offset-md-2 col-md-8 h-100">
                <hr class="mt-0 mb-0" />
                <div class="d-flex align-items-center justify-content-between h-100">
                    <button class="default-button ms-2 invisible">Terug</button>
                <button class="default-button me-2">Volgende dag</button>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
    @media (min-width: 768px) {
        .customer-list {
            -ms-overflow-style: none; /* Internet Explorer 10+ */
            scrollbar-width: none; /* Firefox */
        }

        .customer-list::-webkit-scrollbar {
            display: none; /* Safari and Chrome */
        }
    }
    
    .searchbar-height {
        height: 6em;
    }

    .customer-list {
        height: 89%;
        flex-grow: 1;
        overflow: auto;
        overflow-x: hidden;
    }

    .footer-height {
        height: 10%;
    }
</style>