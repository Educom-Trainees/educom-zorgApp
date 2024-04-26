export function getEmployees() {
    return fetch('http://localhost:8080/employees').then(response => response.json())
}