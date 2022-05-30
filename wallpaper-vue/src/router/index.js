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
    },
    {
        path: '/admin/login',
        name: 'AdminLogin',
        component: import('@/views/admin/Login')
    },
    {
        path: '/admin/register',
        name: 'adminRegister',
        component: import('@/views/admin/Register')
    },
    {
        path: '/admin',
        name: 'admin',
        component: import('@/views/admin/Admin'),
        children: [
            {
                path: '/admin/home',
                name: 'adminHome',
                component: import('@/views/admin/Home')
            },
            {
                path: '/admin/user',
                name: 'adminUser',
                component: import('@/views/admin/User')
            },
            {
                path: '/admin/images',
                name: 'adminImages',
                component: import('@/views/admin/Images')
            },
            {
                path: '/admin/addImage',
                name: 'adminAddImage',
                component: import('@/common/AddImage')
            },
            {
                path: '/admin/feedback',
                name: 'adminFeedback',
                component: import('@/views/admin/Feedback')
            },
            {
                path: '/admin/feedbackResolved',
                name: 'adminFeedbackResolved',
                component: import('@/views/admin/FeedbackResolved')
            },
            {
                path: '/admin/favorite',
                name: 'favorite',
                component: import('@/common/Favorite')
            },
            {
                path: '/admin/edit',
                name: 'edit',
                component: import('@/views/admin/Edit')
            },
            {
                path: '/admin/category',
                name: 'category',
                component: import('@/views/admin/Category')
            },

        ]
    },
    {
        path: '/user',
        name: 'user',
        component: import('@/views/user/User'),
        children: [
            {
                path: '/user/home',
                name: 'userHome',
                component: import('@/views/user/Home')
            },
            {
                path: '/user/favorite',
                name: 'userFavorite',
                component: import('@/common/Favorite')
            },
            {
                path: '/user/feedback',
                name: 'userFeedback',
                component: import('@/views/user/Feedback')
            },
            {
                path: '/user/feedbackList',
                name: 'userFeedbackList',
                component: import('@/views/user/FeedbackList')
            },
            {
                path: '/user/addImage',
                name: 'userAddImage',
                component: import('@/common/AddImage')
            },

        ]
    }

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
