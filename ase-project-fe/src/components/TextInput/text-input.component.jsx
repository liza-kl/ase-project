import React from "react";
import {Form} from "react-bootstrap";

export const TextInputComponent = ({props}) => {
    return(
        <Form.Control type="text" placeholder={props.placeholder} />
    );
}
