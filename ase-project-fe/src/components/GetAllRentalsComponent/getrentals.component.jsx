import React, {useCallback, useEffect, useState} from "react";
import {Button, Table} from "react-bootstrap";
import axios from "axios";
import styled from "styled-components";

export const GetRentalsComponent = () => {
    const [rentals,setRentals] = useState([])

    const getRentals = useCallback(async () => {
        await axios.get(`http://localhost:9000/rental`)
            .then(res => {
                setRentals(res.data)
            })
    }, []);

    useEffect(() => {
        getRentals()
    }, [getRentals])

    const onSubmit = (e) => {
        e.preventDefault()
        getRentals()
    }

    return(
        <div>
            <RentalsList>
                <Table responsive>
                    <thead>
                    <tr>
                        <th>Rental Id</th>
                        <th>Member Id</th>
                        <th>Instrument Type</th>
                        <th>Instrument Serial Number</th>
                        <th>Instrument Manufacturer</th>
                    </tr>
                    </thead>
                    <tbody>
                    {rentals.map(rental =>
                        <tr key={rental.rentalId}>
                            <td>{rental.rentalId}</td>
                            <td>{rental.memberId}</td>
                            <td>{rental.instrumentIdentification.instrumentType}</td>
                            <td>{rental.instrumentIdentification.instrumentSerialNumber}</td>
                            <td>{rental.instrumentIdentification.instrumentManufacturer}</td>
                        </tr>

                    )}
                    </tbody>
                </Table>
            </RentalsList>
            <Button variant="secondary" type="submit" className="w-100" onClick={onSubmit}>
                Get All Rentals
            </Button>
        </div>

    )
}

const RentalsList = styled.div`
    overflow: auto;
    max-height: 10em;
    margin-bottom: 1em;
`
