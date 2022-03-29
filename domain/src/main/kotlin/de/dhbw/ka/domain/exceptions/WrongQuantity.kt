package de.dhbw.ka.domain.exceptions

class WrongQuantity : ExceptionStrategy<IllegalArgumentException> {
    override fun throwsException(): IllegalArgumentException {
        throw IllegalArgumentException("Unfortunately there is no Instrument available at the moment")
    }
}
