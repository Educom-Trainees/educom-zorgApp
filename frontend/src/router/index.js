import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import ListView from '../views/ListView.vue'
import translations from '../config/nl-NL'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView,
            meta: {
                title: '',
            }
        },
        {
            path: '/login',
            name: 'login',
            component: LoginView,
            meta: {
                title: 'Login',
                hideNav: true,
            }
        },
        {
            path: '/medewerkers',
            name: 'employees',
            component: ListView,
            meta: {
                title: translations['employees'],
                listType: 'employees',
                addButtonText: translations['add_employee'],
                singular: translations['employee'],
            }
        },
        {
            path: '/klanten',
            name: 'customers',
            component: ListView,
            meta: {
                title: translations['customers'],
                listType: 'customers',
                addButtonText: translations['add_customer'],
                singular: translations['customer'],
            }
        },
        {
            path: '/taken',
            name: 'tasks',
            component: ListView,
            meta: {
                title: translations['tasks'],
                listType: 'tasks',
                addButtonText: translations['add_task'],
                singular: translations['task'],
            }
        },
        {
            path: '/about',
            name: 'about',
            // route level code-splitting
            // this generates a separate chunk (About.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: () => import('../views/AboutView.vue')
        }
    ]
})

export default router
