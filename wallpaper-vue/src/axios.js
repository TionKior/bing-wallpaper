import axios from 'axios'

// 请求前加上这个前缀
axios.defaults.baseURL = "http://localhost:8080"

let config = {
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json'
    }
}

const _axios = axios.create(config)
_axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    return config
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error)
})

export default _axios
