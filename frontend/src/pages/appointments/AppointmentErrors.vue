<script setup>
    const props = defineProps({
        errors: Array,
        onConfirm: Function,
    })

    function onConfirm() {
        props.onConfirm()
    }

    defineExpose({ onConfirm })
</script>

<template>
    <div> weet u zeker dat u op wilt slaan?</div>
    <div v-for="error in props.errors">
        {{error.message}}
    </div>
</template>