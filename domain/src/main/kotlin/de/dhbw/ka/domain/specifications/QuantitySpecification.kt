package de.dhbw.ka.domain.specifications

import de.dhbw.ka.domain.aggregates.RentalInstrument

object QuantitySpecification : Specification<RentalInstrument> {
    override fun isSatisfiedBy(condition: RentalInstrument): Boolean {
        return condition.quantity > 0
    }
}
