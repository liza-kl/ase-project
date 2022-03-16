import React from "react";
import {InputGroup, FormControl} from "react-bootstrap";

export const TextInputComponent = () => {
    return(<InputGroup className="mb-3">
        <InputGroup.Text id="inputGroup-sizing-default">Default</InputGroup.Text>
        <FormControl
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
        />
    </InputGroup>);
}