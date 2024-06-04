<script setup>
    import { ref } from 'vue'
    import translations from '../config/nl-NL'

    const props = defineProps({
        title: String, //title used by modal
        buttonText: String, //text displayed on button
        buttonClass: String, //class for button which triggers the modal for css
        bodyComponent: Object, //component rendered in body of modal
        properties: Object, //properties passed to bodyComponent
        accept: String, //text displayed on the button to confirm action from modal
    })

    //refs used in the confirm function
    const childRef = ref(null);
    const closeButtonRef = ref(null);

    /**
    * function which calls the child function 'onConfirm' if it exists
    * then closes the modal
    */
    function clickConfirm() {
        childRef.value.onConfirm();
        closeButtonRef.value.click();
    }
</script>

<template>
    <button type="button" :class="props.buttonClass" data-bs-toggle="modal" data-bs-target="#appModal">
        {{props.buttonText}}
    </button>

    <div class="modal fade" id="appModal" tabindex="-1" aria-labelledby="appModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="appModalLabel">{{props.title}}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <component :is="bodyComponent" v-bind="props.properties" ref="childRef"></component>
                </div>
                <div class="modal-footer">
                    <button type="button" class="red-button" data-bs-dismiss="modal" ref="closeButtonRef">{{translations.close}}</button>
                    <button type="button" class="default-button" @click="clickConfirm">{{props.accept}}</button>
                </div>
            </div>
        </div>
    </div>
</template>