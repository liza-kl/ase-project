package de.dhbw.ka.domain.services

import de.dhbw.ka.domain.specifications.EnoughInstrumentsAvailableRule
import de.dhbw.ka.domain.specifications.MemberIsActiveRule
import de.dhbw.ka.domain.valueobjects.RentalRequest
import de.dhbw.ka.domain.valueobjects.RentalRequestResult

class RentalRequestService {

    fun approve(rentalRequest: RentalRequest): RentalRequestResult {
        val listOfRulesToCheck = listOf(MemberIsActiveRule, EnoughInstrumentsAvailableRule)
        val reasonsOfDenial = mutableListOf<String>()
        var approved = true
        listOfRulesToCheck.map {
            if (!it.isSatisfiedBy(rentalRequest)) {
                approved = false
                reasonsOfDenial.add(it.denialMessage)
            }
        }
        return RentalRequestResult(approved = approved, reasonsOfDenial = reasonsOfDenial);
    }
}

