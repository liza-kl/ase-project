package de.dhbw.ka.controllers

import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.repository.InstrumentRepository
import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.rentalinstruments.CreateRentalInstrument
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith
import io.mockk.mockk

/**
 * Used Testing Library: kotest.io
 * Used Mocking Library: mockk.io
 */

/**
 * Test with a mock
 */
class RentalInstrumentControllerKtTest : FunSpec({

    val rentalInstrumentRepository = mockk<RentalInstrumentRepository>(relaxed = true)
    val instrumentRepository = mockk<InstrumentRepository>(relaxed = true)
    val useCase = CreateRentalInstrument(rentalInstrumentRepository, instrumentRepository)

    test("Should not create a Rental Instrument") {
        val rentalInstrument = RentalInstrument(
            InstrumentIdentification("Yamaha", "YHR-567D", "French Horn"),
            3
        )
        val exception = shouldThrow<Exception> {
            useCase.execute(rentalInstrument)
        }
        exception.message should startWith("Instrument must be present in the Instrument database")
    }

})
