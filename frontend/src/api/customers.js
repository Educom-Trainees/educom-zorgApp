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

export function postCustomer(customer) {
    return fetch('http://localhost:8080/customers', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: customer,
    });
}