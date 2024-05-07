import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import EmployeeView from '../views/EmployeeView.vue'
import { customerRoutes } from '../pages/customers/CustomerRoutes'
import { employeeRoutes } from '../pages/employees/EmployeeRoutes'
import { taskRoutes } from '../pages/tasks/TaskRoutes'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        ...customerRoutes,
        ...employeeRoutes,
        ...taskRoutes,
        {
            path: '/',
            name: 'home',
            component: HomeView,
            meta: {
                title: '',
            }
        },
        {
            path: '/emphome',
            name: 'emp',
            component: EmployeeView,
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
