package de.dhbw.ka.domain.entities

import de.dhbw.ka.domain.entities.Instrument

class Musician(name: String, var instrument: Instrument) {
    fun changeInstrument(newInstrument: Instrument) {
        if(this.instrument === newInstrument) {
            throw IllegalArgumentException("Sorry, the musician is already playing this instrument")
        }
        this.instrument = newInstrument
    }

}
