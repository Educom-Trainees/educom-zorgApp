export function getCustomers() {
    return fetch('http://localhost:8080/customers').then(response => response.json())
}