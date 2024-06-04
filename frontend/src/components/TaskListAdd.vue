<script setup>
    import { ref, watch } from "vue"
    import { useQuery, useQueryClient } from 'vue-query'
    import { getCollection } from '../api/collections'
    import { ciEquals } from "../utils/StringComparison"
    import translations from '../config/nl-NL'

    const props = defineProps({
        options: Array,
        addTask: Function,
    })

    const TASKS = 'tasks'

    const queryClient = useQueryClient();

    //vue-query to GET tasks
    const { isLoading, data } = useQuery({
        queryKey: [TASKS],
        queryFn: () => getCollection(TASKS),
        placeholderData: () => {
            const placeholder = queryClient
                .getQueryData([TASKS])
            return placeholder ?? []
        }
    })

    //ref to store standard tasks
    const standardTasks = ref([])

    /**
    * function to update standardTasks ref
    * @param {Array} value list of tasks returned from tasks query
    */
    function updateTasks(value) {
        standardTasks.value = [...value]
    }

    //if data had already been fetched sets tasks ref
    if (!isLoading.value && data.value) {
        updateTasks(data.value)
    }

    //when data changes update tasks ref
    watch(data, (value) => updateTasks(value));

    //refs to track updates to new task
    const taskDescription = ref('')
    const currentId = ref('')

    /**
    * function to update refs to the new task
    * @param {Number} value id of the task. format: default_0 | customer_0
    */
    function updateTask(value) {
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
        taskDescription.value = task.task ?? '';
    }

    /**
    * function to reset task refs
    * @param {String} value value in input form typed by user
    */
    function resetTask(value) {
        currentId.value = ''
        taskDescription.value = value
        // checks if task with same name exists
        var task = props.options.find(o => ciEquals(o.task, value)) //checks for customer specific tasks first
        if (task) {
            currentId.value = 'customer_' + task.id
        } else {
            task = data.value.find(o => ciEquals(o.task, value)) //then checks for default tasks
            if (task) {
                currentId.value = 'default_' + task.id
            }
        }
    }

    /**
    * function to send task to parent (TaskList.vue)
    */
    function sendTask() {
        if (currentId.value) { //if task exists, send that task
            const [taskType, taskId] = currentId.value.split('_') //split type and id
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
        } else if (taskDescription.value) { //check if description is not empty
            props.addTask({ // add new task with specified description
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