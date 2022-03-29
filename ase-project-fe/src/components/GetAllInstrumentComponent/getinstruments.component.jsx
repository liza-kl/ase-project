import React, {useCallback, useEffect, useState} from "react";
import {Button, Table} from "react-bootstrap";
import axios from "axios";
import styled from "styled-components";

export const GetInstrumentsComponent = () => {
    const [instruments,setInstrument] = useState([])

    const getInstruments = useCallback(async () => {
        await axios.get(`http://localhost:9000/instruments`)
            .then(res => {
                setInstrument(res.data)
            })
    }, []);

    useEffect(() => {
        getInstruments()
    }, [getInstruments])

    const onSubmit = (e) => {
        e.preventDefault()
        getInstruments()
    }

    return(
        <div>
            <InstrumentsList>
                <Table responsive>
                    <thead>
                    <tr>
                        <th> Type</th>
                        <th>Manufacturer</th>
                        <th>Serial Number</th>
                        <th>Category</th>
                    </tr>
                    </thead>
                    <tbody>
                    {instruments.map(instrument =>
                        <tr key={instrument.instrumentSerialNumber}>
                            <td>{instrument.instrumentType}</td>
                            <td>{instrument.instrumentManufacturer}</td>
                            <td>{instrument.instrumentSerialNumber}</td>
                            <td>{instrument.instrumentCategory}</td>
                        </tr>
                    )}
                    </tbody>
                </Table>
            </InstrumentsList>
            <Button variant="secondary" type="submit" className="w-100" onClick={onSubmit}>
                Get Instruments
            </Button>
        </div>

    )
}

const InstrumentsList = styled.div`
    overflow: auto;
    max-height: 10em;
    margin-bottom: 1em;
`
