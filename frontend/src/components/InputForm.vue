<script setup>
    //input form properties
    const props = defineProps({
        modelValue: { //modelValue binds to v-model
            type: [String, Number],
            default: '',
        },
        label: String,
        id: String,
    })
</script>

<template>
    <div :class="props.label ? 'form-group mt-2' : ''">
        <label v-if="props.label" :for="props.id">{{props.label}}</label>
        <input class="form-control"
               v-bind="$attrs"
               :value="props.modelValue"
               :id="props.id"
               @input="$emit('update:modelValue', $event.target.value)"
        />
    </div>
</template>