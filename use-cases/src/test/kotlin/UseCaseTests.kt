import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.repository.InstrumentRepository
import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.instruments.CheckIfInstrumentExists
import de.dhbw.ka.rentalinstruments.CheckIfRentalInstrumentExists
import de.dhbw.ka.rentalinstruments.CreateRentalInstrument
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UseCaseTests : FunSpec({
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
})
