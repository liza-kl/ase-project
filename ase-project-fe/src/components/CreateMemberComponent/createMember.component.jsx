import React, {useState} from "react"
import {Button, Form} from "react-bootstrap";
import {createMember} from "../../apis/instruments/members.api";

export const CreateMemberComponent = () => {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [memberStatus, setMemberStatus] = useState("ACTIVE");

    const onSubmit = (event) => {
        event.preventDefault()
       createMember('members', {firstName: firstName, lastName: lastName, memberStatus: memberStatus})
    }

    return (
        <Form>
            <Form.Group className="mb-3" action="post">
                <Form.Control type="text" name="firstName" onChange={(e) => setFirstName(e.target.value)}
                              placeholder="First Name" className="mb-1" value={firstName}/>
                <Form.Control type="text" name="lastName" placeholder="Last Name" className="mb-1" value={lastName}
                              onChange={(e) => setLastName(e.target.value)}/>
                <Form.Select onChange={(e) => setMemberStatus(e.target.value)} value={memberStatus} name="memberStatus">
                    <option>ACTIVE</option>
                    <option>PASSIVE</option>
                </Form.Select>
            </Form.Group>
            <Button variant="secondary" type="submit" className="w-100" onClick={onSubmit}>
                Create Member
            </Button>
        </Form>
    )
}

