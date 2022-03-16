import React from "react"
import {CardGridComponent} from "../CardGrid/card-grid.component";
import styled from "styled-components";
export const LayoutComponent = ({props}) => {
    return(
        <AppContainer className="container-fluid">
            <h1 className="mb-5">Musikvereinsverwaltung</h1>
            <CardGridComponent/>
        </AppContainer>
    )
}

const AppContainer = styled.div`
    flex-direction: column;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 50%
`