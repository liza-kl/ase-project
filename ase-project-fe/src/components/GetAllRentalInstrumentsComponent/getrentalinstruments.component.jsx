import React, {useCallback, useEffect, useState} from "react";
import {Button, Table} from "react-bootstrap";
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
                <Table responsive>
                    <thead>
                    <tr>
                        <th>Type</th>
                        <th>Manufacturer</th>
                        <th>Serial Number</th>
                        <th>Available Quantity</th>
                    </tr>
                    </thead>
                    <tbody>
                    {instruments.map(instrument =>
                        <tr>
                            <td>{instrument.instrumentIdentification.instrumentType}</td>
                            <td>{instrument.instrumentIdentification.instrumentSerialNumber}</td>
                            <td>{instrument.instrumentIdentification.instrumentManufacturer}</td>
                            <td>{instrument.quantity}</td>
                        </tr>

                    )}
                    </tbody>
                </Table>
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
