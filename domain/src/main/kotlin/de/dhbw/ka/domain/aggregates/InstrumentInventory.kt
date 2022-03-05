package de.dhbw.ka.domain.aggregates

import de.dhbw.ka.domain.entities.Instrument

data class InstrumentInventory(val instruments : MutableList<Instrument>)
