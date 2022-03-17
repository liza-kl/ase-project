import axios from "axios";

export function createRentalEntry(urlToPost, rentalInstrumentData) {
    axios.post(`http://localhost:9000/${urlToPost}`, {
        memberId: rentalInstrumentData.memberId,
        instrumentIdentification: {
            instrumentType: rentalInstrumentData.instrumentType,
            instrumentManufacturer: rentalInstrumentData.instrumentManufacturer,
            instrumentSerialNumber: rentalInstrumentData.instrumentSerialNumber,
        },
    })
        .then(function (response) {
            console.log(`You've successfully created a Rental Entry`)
        })
        .catch(function (error) {
            console.log(error);
        });
}
