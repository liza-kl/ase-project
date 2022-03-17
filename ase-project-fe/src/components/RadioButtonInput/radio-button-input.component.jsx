import React from "react";
import {Form} from "react-bootstrap";

export const RadioButtonInputComponent = (props) => {
    return(
        <div key={props.id} className="mb-3">
            <Form.Check
                type="radio"
                name={props.name}
                id={props.id}
                label={props.title}
            />
        </div>
    );
}
