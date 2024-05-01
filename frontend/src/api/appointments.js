export function getAppointments(day) {
    //return fetch('http://localhost:8080/appointments').then(response => response.json())
}

export function getAppointmentsForAWeek(startingDay) {
    return [
        {
            day: 0, appointments: [
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
            ]
        },
        {
            day: 1, appointments: [
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
            ]
        },
        {
            day: 2, appointments: [
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
            ]
        },
        {
            day: 3, appointments: [
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
            ]
        },
        {
            day: 4, appointments: [
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
            ]
        },
        {
            day: 5, appointments: [
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
            ]
        },
        {
            day: 6, appointments: [
                { name: 'guy', address: 'streetstreet', employee: 'fella' },
            ]
        }
    ]
}