export function getTasks() {
    return fetch('http://localhost:8080/tasks').then(response => response.json())
}

export function putTask({ id, task }) {
    return fetch('http://localhost:8080/tasks/' + id, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: task,
    });
}