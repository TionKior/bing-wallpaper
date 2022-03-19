import axios from 'axios'

// 请求前加上这个前缀
axios.defaults.baseURL = "http://localhost:8088"

export default axios