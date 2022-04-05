package de.dhbw.ka.domain.services

import de.dhbw.ka.domain.specifications.EnoughInstrumentsAvailableRule
import de.dhbw.ka.domain.specifications.MemberIsActiveRule
import de.dhbw.ka.domain.valueobjects.RentalRequest

class RentalRequestService {
    fun approve(rentalRequest: RentalRequest) : Boolean {
        val listOfRulesToCheck = listOf(MemberIsActiveRule, EnoughInstrumentsAvailableRule)
        return listOfRulesToCheck.all { it.isSatisfiedBy(rentalRequest) }
    }
}
