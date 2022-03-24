import React from "react"
import {CardGridComponent} from "../CardGrid/card-grid.component";
import styled from "styled-components";
import membersUcData from "../CardGrid/members-uc-data";
import rentalinstrumentsUcData from "../CardGrid/rentalinstruments-uc-data";
import instrumentsUcData from "../CardGrid/instruments-uc-data";
import rentalsUcData from "../CardGrid/rentals-uc-data";

export const HomeComponent = () => {
    return (
        <AppContainer className="container-fluid">
            <h1 className="mt-5 mb-5">Music Society Manager</h1>
                <CardsContainer>
                    <CardGridComponent key={1} useCases={membersUcData}/>
                    <CardGridComponent key={2} useCases={instrumentsUcData}/>
                    <CardGridComponent key={3} useCases={rentalinstrumentsUcData}/>
                    <CardGridComponent key={4} useCases={rentalsUcData}/>
                </CardsContainer>
            </AppContainer>
    )
}

const AppContainer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
`

const CardsContainer = styled.div`
    width: 80%
    @media (max-width: 768px) {
    width: 100%
  }
`
