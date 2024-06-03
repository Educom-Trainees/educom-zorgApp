/**
* parses time string and converts it to object
* @param {String} timeString
* @returns {Object} format {hours: 0, minutes: 0, seconds: 0}
*/
export function parseTime(timeString) {

    if (typeof(timeString) != 'string') return null
    const values = timeString.split(':')
    const timeObj = { hours: +values[0], minutes: +values[1], seconds: +values[2] }

    return timeObj
}

/**
* parses time object and converts it to string
* @param {Object} timeObj
* @returns {String|null} format 'HH:mm:ss' or null if object format is invalid
*/
export function stringifyTime(timeObj) {
    if (!timeObj || typeof (timeObj) != 'object') return null // input is null or not an object
    if (!(Object.hasOwn(timeObj, 'hours') && Object.hasOwn(timeObj, 'minutes'))) return null // input doesn't have necessary properties
    return String(timeObj.hours).padStart(2, '0') + ':' + String(timeObj.minutes).padStart(2, '0') + (Object.hasOwn(timeObj, 'seconds') ? ':' + String(timeObj.seconds).padStart(2, '0') : '')
}



/**
* compares two time objects and returns a number based on the result
* @param {Object} timeObjA
* @param {Object} timeObjB
* @returns {Number} as defined below
* if a > b (i.e. a is later) return positive
* if equal, return 0
* else return negative
* treats null/invalid as 00:00:00
*/
export function compareTime(timeObjA, timeObjB) {
    return convertToNumber(timeObjA) - convertToNumber(timeObjB)
}

/**
* parses time object and converts it to number
* @param {Object} timeObj
* @returns {Number} 12:25:47 results in 122547, invalid object returns 0
*/
function convertToNumber(timeObj) {
    var num = 0
    if (timeObj && typeof (timeObj) == 'object') {
        if ((Object.hasOwn(timeObj, 'hours') && Object.hasOwn(timeObj, 'minutes'))) {
            num = (timeObj.hours * 10000) + (timeObj.minutes * 100)
            num += (Object.hasOwn(timeObj, 'seconds') ? timeObj.seconds : 0)
        }
    }
    return num
}