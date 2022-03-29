import React from "react"
import {Row,Col} from "react-bootstrap"
import {CardComponent} from "../Card/card.component";

export const CardGridComponent = (props) => {
    const useCases = props.useCases.map((useCase) => {
        return(
            <Col xs={12} md={6} className="mb-3">
            <CardComponent
            key={useCase.id}
            title={useCase.cardTitle}
            cardImg={useCase.cardImg}
            ucDescription={useCase.ucDescription}
            ucAction={useCase.ucAction}
            isFormsCard={useCase.isFormsCard}
            ucType={useCase.ucType}
        />
            </Col>)
    })

    return(
            <Row>
                {useCases}
            </Row>
    )
}

