import React, {useCallback, useEffect, useState} from "react";
import {Button, Table} from "react-bootstrap";
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
                <Table responsive>
                    <thead>
                    <tr>
                        <th>MemberId</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    {members.map(member =>
                        <tr key={member.id}>
                            <td>{member.id}</td>
                            <td> {member.firstName} </td>
                            <td>{member.lastName} </td>
                            <td>{member.memberStatus}</td>
                        </tr>
                        )}

                    </tbody>
                </Table>
                <ul>
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
