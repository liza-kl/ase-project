package de.dhbw.ka.instrumentrental

import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.repository.InstrumentRentalEntryRepository
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.domain.valueobjects.RentalRequestApproval
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
        if (findMember != null) {
            RentalRequestApproval(
                member = findMember,
                rentalInstrument = RentalInstrument(
                    instrumentIdentification = instrumentData,
                    quantity = checkQuantityOfRentalInstrument(instrumentData)
                ),
                rentalInstrumentExists = CheckIfRentalInstrumentExists(rentalInstrumentRepository).execute(
                    instrumentData
                )
            )
        }
        rentalInstrumentRepository.decreaseQuantity(instrumentData)
        return instrumentRentalEntryRepository.rentInstrument(memberId, instrumentData)
    }
}
