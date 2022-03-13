package de.dhbw.ka.instrumentrental

import de.dhbw.ka.domain.repository.InstrumentRentalEntryRepository
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.domain.valueobjects.MemberStatus

class BorrowInstrument(
    private val instrumentRentalEntryRepository: InstrumentRentalEntryRepository,
    private val memberRepository: MemberRepository
) {
    fun execute(memberId: Int, instrumentData: InstrumentIdentification): Boolean {
        val findMember = memberRepository.findById(id = memberId)

        if (
            findMember?.memberStatus != MemberStatus("ACTIVE")
        ) {
            throw IllegalArgumentException("Member must be ACTIVE to be able to borrow an Instrument")
        }
        return instrumentRentalEntryRepository.borrowInstrument(memberId, instrumentData)
    }
}