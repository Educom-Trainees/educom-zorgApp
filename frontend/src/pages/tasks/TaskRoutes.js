import ListView from '../../views/ListView.vue'
import TaskView from '../../views/TaskView.vue'
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
            singular: translations['task'],
        }
    },
    {
        path: '/taken/:id',
        name: 'task',
        component: TaskView,
        meta: {
            title: translations['task'],
        }
    }
]