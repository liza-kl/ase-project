import React, {useState} from "react"
import {Button, Form} from "react-bootstrap";
import {RadioButtonInputComponent} from "../RadioButtonInput/radio-button-input.component";
import {createInstrument} from "../../apis/instruments/instruments.api";
import {instrumentCategoryOptions} from "./createInstrument.data";
import styled from "styled-components";
export const CreateInstrumentComponent = () => {
    const [instrumentType, setInstrumentType] = useState("");
    const [instrumentManufacturer, setInstrumentManufacturer] = useState("");
    const [instrumentSerialNumber, setInstrumentSerialNumber] = useState("");
    const [instrumentCategory, setInstrumentCategory] = useState("WOODWIND")

    const onSubmit = (event) => {
        event.preventDefault()
        createInstrument('instruments',
            {
                instrumentType: instrumentType,
                instrumentManufacturer: instrumentManufacturer,
                instrumentSerialNumber: instrumentSerialNumber,
                instrumentCategory: instrumentCategory })
    }

    return (
        <Form>
            <Form.Group className="mb-3" action="post">
                <Form.Control type="text" name="instrumentType" onChange={(e) => setInstrumentType(e.target.value)}
                              placeholder="Instrument Type" className="mb-1" value={instrumentType}/>

                <Form.Control type="text" name="instrumentManufacturer" onChange={(e) => setInstrumentManufacturer(e.target.value)}
                              placeholder="Instrument Manufacturer" className="mb-1" value={instrumentManufacturer}/>
                <Form.Control type="text" name="instrumentSerialNumber" onChange={(e) => setInstrumentSerialNumber(e.target.value)}
                              placeholder="Instrument Serial Number" className="mb-1" value={instrumentSerialNumber}/>
                <Form.Select onChange={(e) => setInstrumentCategory(e.target.value)} value={instrumentCategory} name="instrumentCategory">
                    {instrumentCategoryOptions.map(category => {
                       return <option key={category.id}>{category.category}</option>
                    })}
                </Form.Select>
            </Form.Group>
            <Button variant="secondary" type="submit" className="w-100" onClick={onSubmit}>
                Create Instrument
            </Button>
        </Form>
    )
}

const RadioButtons = styled.div`
    margin: 1em 0 1em 0;
`
