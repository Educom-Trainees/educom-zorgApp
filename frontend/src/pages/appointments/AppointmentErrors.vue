<script setup>
    const props = defineProps({
        errors: Array,
        onConfirm: Function,
    })
    /**
    * wrapper function to use passed in onConfirm function, used by AppModal
    */
    function onConfirm() {
        props.onConfirm()
    }

    defineExpose({ onConfirm }) //expose function so it can be used by parent component
</script>

<template>
    <div> weet u zeker dat u op wilt slaan?</div>
    <div v-for="error in props.errors">
        {{error.message}}
    </div>
</template>