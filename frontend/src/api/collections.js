export function getCollection(type) {
    return fetch('http://localhost:8080/' + type).then(response => response.json())
}
export function getIndividual(type, id) {
    return fetch('http://localhost:8080/' + type + '/' + id).then(response => response.json())
}