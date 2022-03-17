import React, {useState} from "react"
import {Button, Form} from "react-bootstrap";
import {createRentalInstrument} from "../../apis/instruments/rentalinstruments.api";

export const CreateRentalInstrumentComponent = () => {
    const [instrumentType, setInstrumentType] = useState("");
    const [instrumentManufacturer, setInstrumentManufacturer] = useState("");
    const [instrumentSerialNumber, setInstrumentSerialNumber] = useState("");
    const [quantity, setQuantity] = useState(0);

    const onSubmit = (event) => {
        event.preventDefault()
        createRentalInstrument('rentalinstruments',
            {
                instrumentType: instrumentType,
                instrumentManufacturer: instrumentManufacturer,
                instrumentSerialNumber: instrumentSerialNumber,
                quantity: quantity,
            })
    }

    return (
        <Form>
            <Form.Group className="mb-3" action="post">
                <Form.Control type="text" name="instrumentType" onChange={(e) => setInstrumentType(e.target.value)}
                              placeholder="Instrument Type" className="mb-1" value={instrumentType}/>

                <Form.Control type="text" name="instrumentManufacturer"
                              onChange={(e) => setInstrumentManufacturer(e.target.value)}
                              placeholder="Instrument Manufacturer" className="mb-1" value={instrumentManufacturer}/>

                <Form.Control type="text" name="instrumentSerialNumber"
                              onChange={(e) => setInstrumentSerialNumber(e.target.value)}
                              placeholder="Instrument Serial Number" className="mb-1" value={instrumentSerialNumber}/>
                <Form.Control type="text" name="quantity"
                              onChange={(e) => setQuantity(e.target.value)}
                              placeholder="Instrument Quantity" className="mb-1" value={quantity}/>
            </Form.Group>
            <Button variant="secondary" type="submit" className="w-100" onClick={onSubmit}>
                Create Instrument
            </Button>
        </Form>
    )
}
