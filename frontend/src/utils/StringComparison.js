/**
* case insensitive comparison between two strings
* @param {String} a
* @param {String} b
* @returns {Boolean} true if strings are equal
*/
export function ciEquals(a, b) {
    return typeof a === 'string' && typeof b === 'string'
        ? a.localeCompare(b, undefined, { sensitivity: 'accent' }) === 0
        : a === b;
}