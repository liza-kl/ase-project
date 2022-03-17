import React, {useCallback, useEffect, useState} from "react";
import {Button} from "react-bootstrap";
import axios from "axios";
import styled from "styled-components";

export const GetMembersComponent = () => {
    const [members,setMembers] = useState([])

    const getMembers = useCallback(async () => {
        await axios.get(`http://localhost:9000/members`)
            .then(res => {
                setMembers(res.data)
            })
    }, []);

    useEffect(() => {
        getMembers()
    }, [getMembers])

    const onSubmit = (e) => {
        e.preventDefault()
        getMembers()
    }

    return(
        <div>
            <MembersList>
                <ul>
                    {members.map(member => <li key={member.id}> <b>{member.id}</b> {member.firstName} {member.lastName} {member.memberStatus}</li>)}
                </ul>
            </MembersList>
            <Button variant="secondary" type="submit" className="w-100" onClick={onSubmit}>
                Get Members
            </Button>
        </div>

    )
}

const MembersList = styled.div`
    overflow: auto;
    max-height: 10em;
    margin-bottom: 1em;
`
