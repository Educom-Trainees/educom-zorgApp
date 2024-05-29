export function getCollection(type, params = []) {
    const parameters = []
    params.forEach((p) => parameters.push(p.param + '=' + p.value))
    const paramsString = parameters.length > 0 ? '?' + parameters.join('&') : ''
    return fetch('http://localhost:8080/' + type + paramsString).then(response => response.json())
}

export function getIndividual(type, id) {
    return fetch('http://localhost:8080/' + type + '?id=' + id).then(response => response.json())
}

export function putIndividual({ type, id, body }) {
    return fetch('http://localhost:8080/' + type + '?id=' + id, {
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
