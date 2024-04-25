// function to fetch GET request
// url   - url to fetch from
export function fetchData(url) {
    return new Promise((resolve, reject) => {
        fetch(url)
            .then(response => response.json())
            .then(result => {
                resolve(result)
            })
            .catch(err => { reject(err) })
    })
}