<script setup>
    import { ref } from 'vue'

    const props = defineProps({
        title: String,
        buttonText: String,
        bodyComponent: Object,
        buttonClass: String,
    })

    const childRef = ref(null);
    const closeButtonRef = ref(null);

    function clickConfirm() {
        childRef.value.onConfirm();
        closeButtonRef.value.click();
    }
</script>

<template>
    <!-- Button trigger modal -->
    <button type="button" :class="buttonClass" data-bs-toggle="modal" data-bs-target="#appModal">
        {{props.buttonText}}
    </button>

    <!-- Modal -->
    <div class="modal fade" id="appModal" tabindex="-1" aria-labelledby="appModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="appModalLabel">{{props.title}}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <component :is="bodyComponent" ref="childRef"></component>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" ref="closeButtonRef">Close</button>
                    <button type="button" class="btn btn-primary" @click="clickConfirm">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</template>