import ListView from '../../views/ListView.vue'
import CustomerDetailView from './CustomerDetailView.vue'
import NewCustomer from './NewCustomer.vue'
import translations from '../../config/nl-NL'

export const customerRoutes = [
    {
        path: '/klanten',
        name: 'customers.index',
        component: ListView,
        meta: {
            title: translations['customers'],
            listType: 'customers',
            addButtonText: translations['add_customer'],
            singular: translations['a_customer'],
            newComponent: NewCustomer
        }
    },
    {
        path: '/klanten/:id',
        name: 'customers.show',
        component: CustomerDetailView,
        meta: { // needs to be updated
            title: translations['customers'],
            listType: 'customers',
            addButtonText: translations['add_customer'],
            singular: translations['a_customer'],
        }
    }
]