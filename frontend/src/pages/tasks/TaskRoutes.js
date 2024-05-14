import ListView from '../../views/ListView.vue'
import TaskDetailView from './TaskDetailView.vue'
import translations from '../../config/nl-NL'

export const taskRoutes = [
    {
        path: '/taken',
        name: 'tasks',
        component: ListView,
        meta: {
            title: translations['tasks'],
            listType: 'tasks',
            addButtonText: translations['add_task'],
            singular: translations['a_task'],
        }
    },
    {
        path: '/taken/:id',
        name: 'task',
        component: TaskDetailView,
        meta: {
            title: translations['task'],
        }
    }
]