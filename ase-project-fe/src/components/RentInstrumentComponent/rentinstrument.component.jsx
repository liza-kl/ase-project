import React, {useState} from "react"
import {Button, Form} from "react-bootstrap";
import {createRentalEntry} from "../../apis/instruments/rentals.api";

export const RentInstrumentComponent = () => {
    const [memberId, setMemberId] = useState(0);
    const [instrumentType, setInstrumentType] = useState("");
    const [instrumentManufacturer, setInstrumentManufacturer] = useState("");
    const [instrumentSerialNumber, setInstrumentSerialNumber] = useState("");

    const onSubmit = (event) => {
        event.preventDefault()
        createRentalEntry('rental',
            {
                memberId: memberId,
                instrumentType: instrumentType,
                instrumentManufacturer: instrumentManufacturer,
                instrumentSerialNumber: instrumentSerialNumber
            })
    }

    return (
        <Form>
            <Form.Group className="mb-3" action="post">
                <Form.Control type="text" name="memberId" onChange={(e) => setMemberId(e.target.value)}
                              placeholder="Member Id" className="mb-1" value={memberId}/>
                <Form.Control type="text" name="instrumentType" onChange={(e) => setInstrumentType(e.target.value)} placeholder="Instrument Type" className="mb-1" value={instrumentType}/>
                <Form.Control type="text" name="instrumentManufacturer" onChange={(e) => setInstrumentManufacturer(e.target.value)} placeholder="Instrument Manufacturer" className="mb-1" value={instrumentManufacturer}/>
                <Form.Control type="text" name="instrumentSerialNumber" onChange={(e) => setInstrumentSerialNumber(e.target.value)}  placeholder="Instrument Serial Number" className="mb-1" value={instrumentSerialNumber}/>
            </Form.Group>
                <Button variant="secondary" type="submit" className="w-100" onClick={onSubmit}>
                Rent an Instrument
            </Button>
        </Form>
    )
}
