package de.dhbw.ka.instrumentrental

import de.dhbw.ka.domain.repository.InstrumentRentalEntryRepository
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.domain.valueobjects.MemberStatus
import de.dhbw.ka.rentalinstruments.CheckIfRentalInstrumentExists
import de.dhbw.ka.rentalinstruments.GetQuantityOfRentalInstrument

class RentInstrument(
    private val instrumentRentalEntryRepository: InstrumentRentalEntryRepository,
    private val rentalInstrumentRepository: RentalInstrumentRepository,
    private val memberRepository: MemberRepository
) {

    private fun checkQuantityOfRentalInstrument(
        instrumentData: InstrumentIdentification,
        rentalInstrumentRepository: RentalInstrumentRepository = this.rentalInstrumentRepository
    ): Int {
        return GetQuantityOfRentalInstrument(rentalInstrumentRepository).execute(instrumentData)
    }

    fun execute(memberId: Int, instrumentData: InstrumentIdentification): Boolean {
        val findMember = memberRepository.findById(id = memberId)
        if (findMember?.memberStatus != MemberStatus("ACTIVE")) {
            throw IllegalArgumentException("Member must be ACTIVE to be able to borrow an Instrument")
        }
        if (!CheckIfRentalInstrumentExists(rentalInstrumentRepository).execute(instrumentData)) {
            throw IllegalArgumentException("Rental Instrument must be present in the storage in order to be rented")
        }
        if (checkQuantityOfRentalInstrument(instrumentData) <= 0) {
            throw IllegalArgumentException("There is no rental instrument available at the moment!")
        }
        rentalInstrumentRepository.decreaseQuantity(instrumentData)
        return instrumentRentalEntryRepository.borrowInstrument(memberId, instrumentData)
    }
}