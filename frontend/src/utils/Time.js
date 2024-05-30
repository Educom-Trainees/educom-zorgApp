export function parseTime(timeString) {

    if (typeof(timeString) != 'string') return null
    const values = timeString.split(':')
    const timeObj = { hours: +values[0], minutes: +values[1], seconds: +values[2] }

    return timeObj
}

export function stringifyTime(timeObj) {
    if (!timeObj || typeof (timeObj) != 'object') return null // input is null or not an object
    if (!(Object.hasOwn(timeObj, 'hours') && Object.hasOwn(timeObj, 'minutes'))) return null // input doesn't have necessary properties
    return String(timeObj.hours).padStart(2, '0') + ':' + String(timeObj.minutes).padStart(2, '0') + (Object.hasOwn(timeObj, 'seconds') ? ':' + String(timeObj.seconds).padStart(2, '0') : '')
}

//if a > b (i.e. a is later) return positive
//if equal, return 0
//else return negative
//treats null/invalid as 00:00:00
export function compareTime(timeObjA, timeObjB) {
    return convertToNumber(timeObjA) - convertToNumber(timeObjB)
}

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