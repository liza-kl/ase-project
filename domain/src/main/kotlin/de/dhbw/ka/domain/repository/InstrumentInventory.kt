package de.dhbw.ka.domain.repository

import de.dhbw.ka.domain.entities.Instrument


interface InstrumentInventory {
    fun getAllInstruments() : List<Instrument>
    fun addInstrumentToInventory() : Boolean
    fun removeInstrumentFromInventory() : Boolean
    fun getInstrumentsOfCategory() : List<Instrument>
}