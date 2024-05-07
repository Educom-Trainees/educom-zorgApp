import ListView from '../../views/ListView.vue'
import translations from '../../config/nl-NL'

export const customerRoutes = [
    {
        path: '/klanten',
        name: 'customers',
        component: ListView,
        meta: {
            title: translations['customers'],
            listType: 'customers',
            addButtonText: translations['add_customer'],
            singular: translations['a_customer'],
        }
    }
]