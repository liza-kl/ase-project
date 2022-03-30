package de.dhbw.ka.controllers

import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.entities.Instrument
import de.dhbw.ka.domain.repository.InstrumentRepository
import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentCategory
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.instruments.CheckIfInstrumentExists
import de.dhbw.ka.rentalinstruments.CheckIfRentalInstrumentExists
import de.dhbw.ka.rentalinstruments.CreateRentalInstrument
import de.dhbw.ka.server.module
import io.kotest.assertions.ktor.shouldHaveContent
import io.kotest.assertions.ktor.shouldHaveStatus
import io.kotest.assertions.ktor.shouldNotHaveContent
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

/**
 * Used Testing Library: kotest.io
 * Used Mocking Library: mockk.io
 */

class ControllerTests : FunSpec({

    val rentalInstrumentRepository = mockk<RentalInstrumentRepository>(relaxed = true)
    val instrumentRepository = mockk<InstrumentRepository>(relaxed = true)
    val useCase = CreateRentalInstrument(rentalInstrumentRepository, instrumentRepository)
    val rentalInstrument = RentalInstrument(
        InstrumentIdentification("Yamaha", "YHR-567D", "French Horn"),
        3
    )

    afterTest {
        clearMocks(rentalInstrumentRepository)
        clearMocks(instrumentRepository)
    }

    test("Should throw missing Instrument in Database Exception") {
        val exception = shouldThrow<Exception> {
            useCase.execute(rentalInstrument)
        }
        exception.message should startWith("Instrument must be present in the Instrument database")
    }

    test("Should throw missing Rental Instrument Quantity Exception") {
        every { CheckIfInstrumentExists(instrumentRepository).execute(rentalInstrument.instrumentIdentification) } returns true
        every { CheckIfRentalInstrumentExists(rentalInstrumentRepository).execute(rentalInstrument.instrumentIdentification) } returns false
        val rentalInstrumentWithWrongQuantity = RentalInstrument(
            InstrumentIdentification("Yamaha", "YHR-567D", "French Horn"),
            0
        )
        val exception = shouldThrow<Exception> {
            useCase.execute(rentalInstrumentWithWrongQuantity)
        }
        exception.message should startWith("You need to have at least 1 rental instrument in your Rental Instrument storage!")
    }

    test("Should throw RentalInstrument can't be created twice Exception") {
        every { CheckIfInstrumentExists(instrumentRepository).execute(rentalInstrument.instrumentIdentification) } returns true
        every { CheckIfRentalInstrumentExists(rentalInstrumentRepository).execute(rentalInstrument.instrumentIdentification) } returns true
        val exception = shouldThrow<Exception> {
            useCase.execute(rentalInstrument)
        }
        exception.message should startWith("The same Rental Instrument can't be created twice")
    }

    test("Should create Rental Instrument") {
        every { CheckIfInstrumentExists(instrumentRepository).execute(rentalInstrument.instrumentIdentification) } returns true
        every { CheckIfRentalInstrumentExists(rentalInstrumentRepository).execute(rentalInstrument.instrumentIdentification) } returns false
        every { rentalInstrumentRepository.createRentalInstrument(rentalInstrument) } returns true
        useCase.execute(rentalInstrument) shouldBe true
        verify(exactly = 1) { rentalInstrumentRepository.createRentalInstrument(rentalInstrument) }
    }

    test("Should get all Instruments") {
        every { instrumentRepository.getAllInstruments() } returns listOf(
            Instrument(
                InstrumentIdentification("Yamaha", "YHR-567D", "French Horn"),
                InstrumentCategory("BRASS"),
            )
        )
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/instruments").apply {
                response shouldHaveStatus HttpStatusCode.OK
                response shouldNotHaveContent "failure"
                response shouldHaveContent "[{\"instrumentType\":\"French Horn\"," +
                        "\"instrumentManufacturer\":\"Yamaha\"," +
                        "\"instrumentSerialNumber\":\"French Horn\"," +
                        "\"instrumentCategory\":\"BRASS\"}]"
            }
        }
    }
})
