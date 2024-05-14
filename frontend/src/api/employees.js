export function getEmployees() {
    return fetch('http://localhost:8080/employees').then(response => response.json())
}

export function putEmployee({ id, employee }) {
    return fetch('http://localhost:8080/employees/' + id, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: employee,
    });
}