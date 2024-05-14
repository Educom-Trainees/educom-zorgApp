export function getCustomers() {
    return fetch('http://localhost:8080/customers').then(response => response.json())
}

export function putCustomer({ id, customer }) {
    return fetch('http://localhost:8080/customers/' + id, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: customer,
    });
}