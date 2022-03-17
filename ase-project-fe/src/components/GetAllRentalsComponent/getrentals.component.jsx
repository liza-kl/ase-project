import React, {useCallback, useEffect, useState} from "react";
import {Button} from "react-bootstrap";
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
            <MembersList>
                <ul>
                    {rentals.map(rental =>
                        <li key={rental.rentalId}> <b>{rental.rentalId}</b> {rental.memberId} {rental.instrumentIdentification.instrumentType}
                            {rental.instrumentIdentification.instrumentSerialNumber} {rental.instrumentIdentification.instrumentManufacturer}
                        </li>)}
                </ul>
            </MembersList>
            <Button variant="secondary" type="submit" className="w-100" onClick={onSubmit}>
                Get All Rentals
            </Button>
        </div>

    )
}

const MembersList = styled.div`
    overflow: auto;
    max-height: 10em;
    margin-bottom: 1em;
`
