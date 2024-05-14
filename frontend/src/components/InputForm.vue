<script setup>
    const props = defineProps({
        modelValue: {
            type: [String, Number],
            default: '',
        },
        label: String,
        id: String,
    })
</script>

<template>
    <div class="form-group mt-2">
        <label :for="props.id">{{props.label}}</label>
        <input class="form-control"
               :value="props.modelValue"
               :id="props.id"
               @input="$emit('update:modelValue', $event.target.value)"
        />
    </div>
</template>