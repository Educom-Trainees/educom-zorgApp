import { createApp } from 'vue'
import { VueQueryPlugin } from 'vue-query'
import App from './App.vue'
import router from './router'

import './assets/main.css'
import 'bootstrap-icons/font/bootstrap-icons.css'

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'

const app = createApp(App)

app.component('VueDatePicker', VueDatePicker);

app.use(router)
app.use(VueQueryPlugin)

app.mount('#app')
