import React from "react"
import {Card} from "react-bootstrap"
import {CreateMemberComponent} from "../CreateMemberComponent/createMember.component";
import {GetMembersComponent} from "../GetMemberComponent/getMembers.component";
import {CreateInstrumentComponent} from "../CreateInstrumentComponent/createinstrument.component";
import {GetInstrumentsComponent} from "../GetAllInstrumentComponent/getinstruments.component";
import {CreateRentalInstrumentComponent} from "../CreateRentalInstrumentComponent/createrentalinstrument.component";
import {GetRentalInstrumentsComponent} from "../GetAllRentalInstrumentsComponent/getrentalinstruments.component";
import {GetRentalsComponent} from "../GetAllRentalsComponent/getrentals.component";
import {RentInstrumentComponent} from "../RentInstrumentComponent/rentinstrument.component";

export const CardComponent = (props) => {
    return (
        <Card className="h-100">
            <Card.Body>
                <Card.Title>{props.title}</Card.Title>
                <Card.Text>
                    {props.ucDescription}
                </Card.Text>
                {
                    props.ucType === "createMember" ? <CreateMemberComponent/> : ""
                }
                {
                    props.ucType === "getMembers" ? <GetMembersComponent/> : ""
                }
                {
                    props.ucType === "createInstrument" ? <CreateInstrumentComponent/> : ""
                }
                {
                    props.ucType === "getInstruments" ? <GetInstrumentsComponent/> : ""
                }
                {
                    props.ucType === "createRentalInstrument" ? <CreateRentalInstrumentComponent/> : ""
                }
                {
                    props.ucType === "getRentalInstruments" ? <GetRentalInstrumentsComponent/> : ""
                }
                {
                    props.ucType === "getAllRentals" ? <GetRentalsComponent/> : ""
                }
                {
                    props.ucType === "createRentalEntry" ? <RentInstrumentComponent/> : ""
                }
            </Card.Body>
        </Card>
    )
}
