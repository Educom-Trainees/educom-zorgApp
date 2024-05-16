export function getCollection(type) {
    return fetch('http://localhost:8080/' + type).then(response => response.json())
}

export function getIndividual(type, id) {
    return fetch('http://localhost:8080/' + type + '/' + id).then(response => response.json())
}

export function putIndividual({ type, id, body }) {
    return fetch('http://localhost:8080/' + type + '/' + id, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: body,
    });
}

export function postIndividual({ type, body }) {
    return fetch('http://localhost:8080/' + type, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: body,
    });
}
