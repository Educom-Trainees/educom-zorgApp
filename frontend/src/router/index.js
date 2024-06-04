import { createRouter, createWebHistory } from 'vue-router'
import { appointmentRoutes } from '../pages/appointments/AppointmentRoutes'
import { customerRoutes } from '../pages/customers/CustomerRoutes'
import { employeeRoutes } from '../pages/employees/EmployeeRoutes'
import { taskRoutes } from '../pages/tasks/TaskRoutes'
import EmployeeView from '../views/EmployeeView.vue'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        ...appointmentRoutes,
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
        }
    ]
})

export default router
