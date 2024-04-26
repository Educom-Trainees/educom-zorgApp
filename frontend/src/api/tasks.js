export function getTasks() {
    return fetch('http://localhost:8080/tasks').then(response => response.json())
}