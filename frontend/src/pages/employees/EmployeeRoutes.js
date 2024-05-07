import ListView from '../../views/ListView.vue'
import translations from '../../config/nl-NL'

export const employeeRoutes = [
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
    }
]