package de.dhbw.ka.domain.services

import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.domain.valueobjects.MemberName
import de.dhbw.ka.domain.valueobjects.MemberStatus
import de.dhbw.ka.domain.valueobjects.RentalRequest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith

class RentalRequestServiceTest : FunSpec({

    test("Should approve the request") {
        val rentalRequest = RentalRequest(
            Member(1, MemberName("Hannah", "Müller"), MemberStatus("ACTIVE")),
            RentalInstrument(InstrumentIdentification("Yamaha", "123", "Oboe"), 1)
        )
        RentalRequestService().approve(rentalRequest) shouldBe true
    }

    test("Should deny the request due to the unsufficient member status ") {
        val rentalRequest = RentalRequest(
            Member(1, MemberName("Hannah", "Müller"), MemberStatus("PASSIVE")),
            RentalInstrument(InstrumentIdentification("Yamaha", "123", "Oboe"), 2)
        )
        val exception = shouldThrow<Exception> {
            RentalRequestService().approve(
                rentalRequest)
        }
        exception.message should startWith("Unfortunately you have the wrong MemberStatus in order to rent an instrument")
    }

    test("Should deny the request due to the unsufficient quantity of Instruments ") {
        val rentalRequest = RentalRequest(
            Member(1, MemberName("Hannah", "Müller"), MemberStatus("ACTIVE")),
            RentalInstrument(InstrumentIdentification("Yamaha", "123", "Oboe"), 0)
        )

        val exception = shouldThrow<Exception> {
            RentalRequestService().approve(
                rentalRequest)
        }
        exception.message should startWith("Unfortunately there is no Instrument available at the moment")
    }

})
