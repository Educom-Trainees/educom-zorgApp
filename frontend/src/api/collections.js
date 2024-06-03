/**
* GET method for collection of items of a certain type (e.g. tasks, customers)
* params are parameters to be passed in the URL (i.e. ?date='2024-01-01')
* @param {String} type
* @param {Array} [params=[]] list of {param: '', value: ''} items
* @return {Array} parsed response from db, should be an array
*/
export function getCollection(type, params = []) {
    const parameters = []
    params.forEach((p) => parameters.push(p.param + '=' + p.value))
    const paramsString = parameters.length > 0 ? '?' + parameters.join('&') : ''
    return fetch('http://localhost:8080/' + type + paramsString).then(response => response.json())
}

/**
* GET method for individual item of a certain type
* @param {String} type
* @param {Number|String} id
* @return {Object} parsed response from db, should be an object
*/
export function getIndividual(type, id) {
    return fetch('http://localhost:8080/' + type + '?id=' + id).then(response => response.json())
}

/**
* PUT method for individual item of a certain type
* vue-query mutations expect one argument so multiple arguments need to be combined and then destructured
* @param {Object} {type, id, body}
* @param {String} type
* @param {Number|String} id
* @param {Object} body JSON of item to be updated
* @return {Promise} response from db
*/
export function putIndividual({ type, id, body }) {
    return fetch('http://localhost:8080/' + type + '?id=' + id, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: body,
    });
}

/**
* POST method for individual item of a certain type
* vue-query mutations expect one argument so multiple arguments need to be combined and then destructured
* @param {Object} {type, body}
* @param {String} type
* @param {Object} body JSON of item to be updated
* @return {Promise} response from db
*/
export function postIndividual({ type, body }) {
    return fetch('http://localhost:8080/' + type, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: body,
    });
}
