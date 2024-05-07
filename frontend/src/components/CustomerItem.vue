<script setup>
    import { computed } from "vue";
    import checkmark from '../assets/checkmark.png'
    import ellipsis from '../assets/ellipsis.png'

    const props = defineProps(['initials', 'name', 'address', 'taskprogress'])
    
    const img_src = (props['taskprogress'] == "done" ? checkmark : (props['taskprogress'] == "in_progress" ? ellipsis : ''));

</script>

<template>
    <RouterLink to="../custdetail">
        <div class="row mb-2 justify-content-center">
            <div class="row align-items-center">
                <div class="col-2">
                    <div class="zapp-gradient circle-initials">
                        {{props.initials}}
                    </div>
                </div>
                <div class="col-8 d-flex flex-column">
                    <div class="customer-text-lg">
                        {{props.name}}
                    </div>
                    <div class="customer-text-sm">
                        {{props.address}}
                    </div>
                </div>
                <div class="col-2 check-or-in-progress">
                    <div class="container d-flex flex-row-reverse">

                    <img class="d-inline-block" :src="img_src" height="64"/>
                    </div>
                </div>
            </div>
        </div>
    </RouterLink>
</template>