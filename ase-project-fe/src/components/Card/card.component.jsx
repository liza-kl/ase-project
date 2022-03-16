import React from "react"
import {Card, Button} from "react-bootstrap"
import {TextInputComponent} from "../TextInput/text-input.component";

export const CardComponent = (props) => {
    return(
        <Card className="h-100">
            <Card.Img variant="top" src={props.cardImg}></Card.Img>
            <Card.Body>
                <Card.Title>{props.title}</Card.Title>
                <Card.Text>
                    {props.ucDescription}
                </Card.Text>
                {  props.isFormsCard ? <TextInputComponent/> : ''}
                <Button variant="secondary">{props.ucAction}</Button>
            </Card.Body>
        </Card>
    )
}
