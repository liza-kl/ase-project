import React, {useCallback, useEffect, useState} from "react";
import {Button} from "react-bootstrap";
import axios from "axios";
import styled from "styled-components";

export const GetRentalInstrumentsComponent = () => {
    const [instruments,setInstrument] = useState([])

    const getRentalInstruments = useCallback(async () => {
        await axios.get(`http://localhost:9000/rentalinstruments`)
            .then(res => {
                console.log(res.data)
                setInstrument(res.data)
            })
    }, []);

    useEffect(() => {
        getRentalInstruments()
    }, [getRentalInstruments])

    const onSubmit = (e) => {
        e.preventDefault()
        getRentalInstruments()
    }

    return(
        <div>
            <InstrumentsList>
                <ul>
                    {instruments.map(instrument =>
                        <li>
                            <b>{instrument.instrumentIdentification.instrumentType}</b>
                            {instrument.instrumentIdentification.instrumentSerialNumber}
                            {instrument.instrumentIdentification.instrumentManufacturer}
                            {instrument.quantity}
                        </li>
                    )}
                </ul>
            </InstrumentsList>
            <Button variant="secondary" type="submit" className="w-100" onClick={onSubmit}>
                Get Rental Instruments
            </Button>
        </div>

    )
}

const InstrumentsList = styled.div`
    overflow: auto;
    max-height: 10em;
    margin-bottom: 1em;
`
