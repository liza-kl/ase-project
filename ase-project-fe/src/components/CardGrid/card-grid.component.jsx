import React from "react"
import {Row,Col} from "react-bootstrap"
import {CardComponent} from "../Card/card.component";
import {useCases} from "./card-grid.data";

export const CardGridComponent = () => {
    const useCasesCard = useCases.map((useCase) => {
        return(
            <Col xs={12} md={4} className="mb-3">
            <CardComponent key={useCase.id}
            title={useCase.cardTitle}
            cardImg={useCase.cardImg}
            ucDescription={useCase.ucDescription}
            ucAction={useCase.ucAction}
            isFormsCard={useCase.isFormsCard}
        />
            </Col>)
    })

    return(
            <Row>
                {useCasesCard}
            </Row>
    )
}

