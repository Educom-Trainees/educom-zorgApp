<script setup>
    import { computed } from "vue"
    import translations from '../../config/nl-NL'

    const props = defineProps({
        modelValue: { //binds to v-model
            type: [Number],
            default: 1,
        },
        label: String,
        id: String,
        options: Array,
    })

    //computed customer value used to display values above selector
    const customer = computed(() => props.options.find(o => o.id == props.modelValue))
</script>

<template>
    <div class="select-card">
        <div class="title zapp-gradient ps-2 fs-5">{{translations.customer}}</div>
        <div class="select-card-body zapp-gradient-continue">
            <div class="select-card-content">

                <div v-if="!!customer">{{customer.name}}</div>
                <div class="ms-3">
                    <div v-if="!!customer">
                        <div v-for="part in customer.address.split(',')">
                            {{part}}
                        </div>
                    </div>
                </div>
                <select class="form-select bg-light"
                        disabled
                        :value="props.modelValue"
                        :id="props.id"
                        @input="$emit('update:modelValue', $event.target.value)">
                    <option v-for="option in options" :value="option.id">
                        {{ option.name }}
                    </option>
                </select>
            </div>
        </div>
    </div>
    
</template>

<style scoped>
    .title {
        font-weight: bold;
        color: white;
    }
</style>