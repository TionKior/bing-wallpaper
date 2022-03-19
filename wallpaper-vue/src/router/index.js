import {createRouter, createWebHistory} from 'vue-router'
import ImageDetail from "@/views/ImageDetail";
import Search from "@/views/Search";
import Login from "@/views/Login";

const routes = [
    {
        path: '/',
        name: 'index',
        // 设置懒加载可以默认 '/' 是
        component: import('@/views/Images')
    },
    {
        path: '/images',
        name: 'Images',
        component: import('@/views/Images')
    },
    {
        path: '/imageDetail',
        name: 'ImageDetail',
        component: ImageDetail
    },
    {
        path: '/search',
        name: 'Search',
        component: Search

    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
