<script setup>
    import { computed } from "vue"
    import translations from '../../config/nl-NL'

    const props = defineProps({
        modelValue: { //binds to v-model
            type: [Number],
            default: '',
        },
        label: String,
        id: String,
        options: Array,
        signInTime: String,
        signOutTime: String,
    })

    //computed values used to display values above selector
    const signInString = computed(() => props.signInTime ? props.signInTime.substring(0,5) : '--:--')
    const signOutString = computed(() => props.signOutTime ? props.signOutTime.substring(0, 5) : '--:--')

    const employee = computed(() => props.options.find(o => o.id == props.modelValue))
</script>

<template>
    <div class="select-card">
        <div class="title zapp-gradient ps-2 fs-5">{{translations.employee}}</div>
        <div class="select-card-body zapp-gradient-continue">
            <div class="select-card-content">

                <div v-if="!!employee" class="fs-5">{{employee.name}}</div>
                <div class="ms-3">
                    <div class="row">
                        <div class="offset-1 col-5">{{ translations.signed_in + ':' }}</div>
                        <div class="col-6 fw-bold font-monospace">{{signInString}}</div>
                    </div>
                    <div class="row">
                        <div class="offset-1 col-5">{{ translations.signed_out + ':' }}</div>
                        <div class="col-6 fw-bold font-monospace">{{signOutString}}</div>
                    </div>
                </div>
                <select class="form-select bg-light"
                        :value="props.modelValue"
                        :id="props.id"
                        @input="$emit('update:modelValue', +$event.target.value)">
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