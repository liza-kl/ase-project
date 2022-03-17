import React, {useCallback, useEffect, useState} from "react";
import {Button} from "react-bootstrap";
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
                <ul>
                    {instruments.map(instrument =>
                        <li key={instrument.instrumentSerialNumber}>
                            <b>{instrument.instrumentType}</b> {instrument.instrumentManufacturer} {instrument.instrumentSerialNumber} {instrument.instrumentCategory}
                        </li>
                    )}
                </ul>
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
