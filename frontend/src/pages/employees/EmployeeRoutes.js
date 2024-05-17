import ListView from '../../views/ListView.vue'
import EmployeeDetailView from './EmployeeDetailView.vue'
import translations from '../../config/nl-NL'
import NewEmployee from './NewEmployee.vue'

export const employeeRoutes = [
    {
        path: '/medewerkers',
        name: 'employees',
        component: ListView,
        meta: {
            title: translations['employees'],
            listType: 'employees',
            addButtonText: translations['add_employee'],
            singular: translations['an_employee'],
            newComponent: NewEmployee
        }
    },
    {
        path: '/medewerkers/:id',
        name: 'employees.show',
        component: EmployeeDetailView,
        meta: {
            title: translations['employees'],
            listType: 'employees',
            addButtonText: translations['add_employee'],
            singular: translations['an_employee'],
        }
    }
]