<script setup>
    import { ref, watch } from "vue";
    import { getCollection } from '../api/collections'
    import { useQuery, useQueryClient } from 'vue-query'
    import translations from '../config/nl-NL'
    import { ciEquals } from "../utils/StringComparison";

    const props = defineProps({
        options: Array,
        addTask: Function,
    })

    const TASKS = 'tasks'

    const queryClient = useQueryClient();

    const { isLoading, data } = useQuery({
        queryKey: [TASKS],
        queryFn: () => getCollection(TASKS), //might want to move customers to route meta info
        placeholderData: () => {
            // Use the smaller/list version of the customer from the CUSTOMERS
            // query as the placeholder data for this customer query
            const placeholder = queryClient
                .getQueryData([TASKS])
            return placeholder ?? []
        }
    })

    const standardTasks = ref([])

    function updateTasks(value) {
        standardTasks.value = [...value]
    }

    if (!isLoading.value && data.value) {
        updateTasks(data.value)
    }

    watch(data, (value) => updateTasks(value));

    const taskDescription = ref('')

    const currentId = ref('')

    const updateTask = (value) => {
        currentId.value = value
        const [taskType, taskId] = currentId.value.split('_')
        var task;
        switch (taskType) {
            case 'default':
                task = data.value.find(o => o.id == taskId)
                break;
            case 'customer':
                task = props.options.find(o => o.id == taskId)
                break;
        }
        console.log(typeof (props.options), props.options, task)
        taskDescription.value = task.task ?? '';
    }

    const resetTask = (value) => {
        currentId.value = ''
        taskDescription.value = value
        // checks if task with same name exists
        var task = props.options.find(o => ciEquals(o.task, value))
        if (task) {
            currentId.value = 'customer_' + task.id
        } else {
            task = data.value.find(o => ciEquals(o.task, value))
            if (task) {
                currentId.value = 'default_' + task.id
            }
        }
    }
    const sendTask = () => {
        console.log('sending')
        if (currentId.value) {
            const [taskType, taskId] = currentId.value.split('_')
            switch (taskType) {
                case 'default':
                    const newDTask = data.value.find(o => o.id == taskId)
                    props.addTask(JSON.parse(JSON.stringify(newDTask)))
                    break;
                case 'customer':
                    const newCTask = props.options.find(o => o.id == taskId)
                    props.addTask(JSON.parse(JSON.stringify(newCTask)))
                    break;
            }
            //props.addTask(props.options.find(o => o.id == currentId.value))
        } else if (taskDescription.value) {
            props.addTask({
                task: taskDescription.value,
                additional_info: '',
                completed: false,
                noteEmployee: '',
                noteRead: false,
            })
        }
    }

</script>

<template>
    <td colspan="4">
        <div class="input-group">
            <input class="form-control" v-model="taskDescription" :placeholder="translations.add_task" @input="resetTask($event.target.value)" @keyup.enter="sendTask">
            <select class="form-select bg-light"
                    :value="currentId"
                    @input="updateTask($event.target.value)">
                <optgroup v-if="options && options.length > 0" :label="translations.customer_tasks">
                    <option v-for="option in options" :value="'customer_'+option.id">
                        {{ option.task }}
                    </option>
                </optgroup>
                <optgroup v-if="!isLoading || data" :label="translations.standard_tasks">
                    <option v-for="option in data" :value="'default_'+option.id">
                        {{ option.task }}
                    </option>
                </optgroup>
            </select>
        </div>
    </td>
    <td><button class="default-button px-3 py-1 bi bi-plus" @click="sendTask"></button></td>
</template>