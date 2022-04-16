package de.dhbw.ka.instrumentrental

import de.dhbw.ka.domain.repository.InstrumentRentalEntryRepository
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.domain.services.RentalRequestService
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.domain.valueobjects.RentalRequest

class RentInstrument(
    private val instrumentRentalEntryRepository: InstrumentRentalEntryRepository,
    private val rentalInstrumentRepository: RentalInstrumentRepository,
    private val memberRepository: MemberRepository,
) {

    fun execute(memberId: Int, instrumentData: InstrumentIdentification): Boolean {
        val member = memberRepository.findById(id = memberId) ?: throw IllegalArgumentException("No Member with this Id")
        val rentalInstrument = rentalInstrumentRepository.getRentalInstrumentByIdentification(instrumentData) ?: throw IllegalArgumentException("No Instrument with this Id")
        val rentalApproved = RentalRequestService().approve(rentalRequest = RentalRequest(member,rentalInstrument))

        if(rentalApproved) {
            rentalInstrumentRepository.decreaseQuantity(instrumentData)
            return instrumentRentalEntryRepository.rentInstrument(memberId, instrumentData)
        }
        return false
    }
}
