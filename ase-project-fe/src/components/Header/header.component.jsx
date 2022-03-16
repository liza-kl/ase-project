import React from "react"
import "react-bootstrap"
import {NavLink} from "react-bootstrap";
import {Header,HeaderTitle,NavLinks} from "./header.styles";

export const HeaderComponent = (props) => {
    return (
        <Header className="row">
            <HeaderTitle xs={4} className="col">
                {props.headerTitle}
            </HeaderTitle>
            <NavLinks xs={8} className="col">
                <NavLink href="">GitHub</NavLink>
                <NavLink href="">Documentation</NavLink>
            </NavLinks>
        </Header>
    )
}
