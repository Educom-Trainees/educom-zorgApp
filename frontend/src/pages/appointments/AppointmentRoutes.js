import ListView from '../../views/ListView.vue'
import AppointmentDetailView from './AppointmentDetailView.vue'
import translations from '../../config/nl-NL'

export const appointmentRoutes = [
    {
        path: '/afspraken/:id',
        name: 'appointments.show',
        component: AppointmentDetailView,
        meta: { // needs to be updated
            title: translations['appointments'],
            listType: 'customers',
            addButtonText: translations['add_customer'],
            singular: translations['a_customer'],
        }
    }
]