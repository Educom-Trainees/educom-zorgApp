<script setup>
    import { computed } from "vue"
    //icon imports
    import checkmark from '../assets/checkmark.png'
    import ellipsis from '../assets/ellipsis.png'
    import broom from '../assets/broom.png'
    import blank from '../assets/blank.png'

    //property definitions (all of type String, could be explicitly defined)
    const props = defineProps(['initials', 'name', 'address', 'taskprogress'])

    //picks which icon to use based on taskprogress prop
    const img_src = (props['taskprogress'] == "done" ? checkmark : (props['taskprogress'] == "in_progress" ? broom : ellipsis));

</script>

<template>
    <RouterLink to="../custdetail"> <!-- router link not linked to anything yet -->
        <div class="row mb-2 justify-content-center pr-0">
            <div class="row align-items-center">
                <div class="col-2 h-100 ps-1">
                    <div class="zapp-gradient circle-initials">
                        {{props.initials}}
                    </div>
                </div>
                <div class="col-8 d-flex flex-column">
                    <div class="customer-text-lg">
                        {{props.name}}
                    </div>
                    <div class="customer-text-sm text-truncate">
                        {{props.address}}
                    </div>
                </div>
                <div class="col-2 check-or-in-progress">
                    <div class="container d-flex flex-row-reverse pe-0">

                    <img class="d-inline-block" :src="img_src" height="100%"/>
                    </div>
                </div>
            </div>
        </div>
    </RouterLink>
</template>