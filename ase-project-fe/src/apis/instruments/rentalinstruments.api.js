import axios from 'axios';

export function createRentalInstrument(urlToPost, rentalInstrumentData) {
    axios.post(`http://localhost:9000/${urlToPost}`, {
        instrumentIdentification: {
            instrumentType: rentalInstrumentData.instrumentType,
            instrumentManufacturer: rentalInstrumentData.instrumentManufacturer,
            instrumentSerialNumber: rentalInstrumentData.instrumentSerialNumber,
        },
        quantity: rentalInstrumentData.quantity
    })
        .then(function (response) {
            console.log(`You've successfully created a Rental Instrument`)
        })
        .catch(function (error) {
            console.log(error);
        });
}
