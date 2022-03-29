package de.dhbw.ka.domain.exceptions

class RentalInstrumentDoesntExist : ExceptionStrategy<IllegalArgumentException> {
    override fun throwsException(): IllegalArgumentException {
        throw IllegalArgumentException("Rental Instrument must be present in the storage in order to be rented")
    }
}
