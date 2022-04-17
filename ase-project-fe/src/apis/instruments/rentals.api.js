import axios from "axios";

export function createRentalEntry(urlToPost, rentalInstrumentData) {
    axios.post(`http://localhost:9000/${urlToPost}`, {
        memberId: rentalInstrumentData.memberId,
        instrumentIdentification: {
            instrumentType: rentalInstrumentData.instrumentType,
            instrumentSerialNumber: rentalInstrumentData.instrumentSerialNumber,
            instrumentManufacturer: rentalInstrumentData.instrumentManufacturer,
        },
    })
        .then(function (response) {
            console.log(`You've successfully created a Rental Entry`)
        })
        .catch(function (error) {
            console.log(error);
        });
}
