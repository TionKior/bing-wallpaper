import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

const app = createApp(App)
app.use(store)
app.use(router)

import $ from 'jquery'

app.config.globalProperties.$ = $

// 引入bootstrap样式
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'

// 使用ElementPlus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

app.use(ElementPlus)

import axios from './axios.js'
app.config.globalProperties.$http = axios

app.mount('#app')
