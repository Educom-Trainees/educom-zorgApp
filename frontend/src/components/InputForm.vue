<script setup>
    const props = defineProps({
        type: {
            type: String,
            default: 'text',
            validator(value) {
                return ['text', 'number', 'email', 'password', 'hidden'].includes(value);
            }
        },
        label: String,
        value: String,
        id: String,
    })
</script>

<template>
    <label :for="props.id">{{props.label}}</label>
    <input 
           :type="props.type"
           v-model="props.value"
           :id="props.id"
    />
</template>