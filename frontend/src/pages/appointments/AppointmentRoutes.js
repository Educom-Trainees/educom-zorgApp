import AppointmentDetailView from './AppointmentDetailView.vue'
import translations from '../../config/nl-NL'

export const appointmentRoutes = [
    {
        path: '/afspraken/:id',
        name: 'appointments.show',
        component: AppointmentDetailView,
    }
]