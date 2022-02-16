package de.dhbw.ka.domain.entities

import de.dhbw.ka.domain.entities.Instrument
import de.dhbw.ka.domain.entities.MusicSocietyMember

class Musician(var instrument: Instrument, var musicSocietyMember: MusicSocietyMember) {
    fun changeInstrument(newInstrument: Instrument) {
        if(this.instrument === newInstrument) {
            throw IllegalArgumentException("Sorry, the musician is already playing this instrument")
        }
        this.instrument = newInstrument
    }

    fun createMusician() {
        if (!this.musicSocietyMember.playsInstrument) {
            throw IllegalArgumentException("This person can't be a musician because she or he doesn't play an instrument")
        }
    }
}
