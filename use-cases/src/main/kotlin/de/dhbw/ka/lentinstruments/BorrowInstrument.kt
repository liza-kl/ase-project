package de.dhbw.ka.lentinstruments

import de.dhbw.ka.domain.entities.Instrument
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.LentInstrumentRepository
import de.dhbw.ka.domain.valueobjects.MemberStatus

class BorrowInstrument(private val lentInstrumentRepository : LentInstrumentRepository) {

    fun execute(memberData: Member, instrumentData: Instrument) {
        if(memberData.memberStatus != MemberStatus("ACTIVE")) {
            throw IllegalArgumentException("Member must be ACTIVE to be able to borrow an instrument ")
        }
    }
}