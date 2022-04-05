package de.dhbw.ka.domain.services

import de.dhbw.ka.domain.exceptions.WrongMemberStatus
import de.dhbw.ka.domain.exceptions.WrongQuantity
import de.dhbw.ka.domain.specifications.EnoughInstrumentsAvailableRule
import de.dhbw.ka.domain.specifications.MemberIsActiveRule
import de.dhbw.ka.domain.valueobjects.RentalRequest

class RentalRequestService {
    fun approve(rentalRequest: RentalRequest) : Boolean {
        val listOfRulesToCheck = listOf(MemberIsActiveRule, EnoughInstrumentsAvailableRule)
         listOfRulesToCheck.map {
                if(!it.isSatisfiedBy(rentalRequest)) {
                    when(it) {
                        MemberIsActiveRule -> throw WrongMemberStatus().throwsException()
                        EnoughInstrumentsAvailableRule -> throw WrongQuantity().throwsException()
                    }
                }
        }
        return true
    }
}
