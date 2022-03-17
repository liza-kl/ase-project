import axios from 'axios';

export function createInstrument(urlToPost, instrumentData) {
    axios.post(`http://localhost:9000/${urlToPost}`, {
        instrumentType: instrumentData.instrumentType,
        instrumentManufacturer: instrumentData.instrumentManufacturer,
        instrumentSerialNumber: instrumentData.instrumentSerialNumber,
        instrumentCategory: instrumentData.instrumentCategory,
    })
        .then(function (response) {
            console.log(`You've successfully created an Instrument`)
        })
        .catch(function (error) {
            console.log(error);
        });
}
