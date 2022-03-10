package de.dhbw.ka.controllers

import de.dhbw.ka.domain.repository.LentInstrumentRepository
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.dto.InstrumentDTO
import de.dhbw.ka.dto.LentInstrumentDTO
import de.dhbw.ka.lentinstruments.BorrowInstrument
import de.dhbw.ka.repository.LentInstrumentRepositoryImpl
import de.dhbw.ka.repository.MembersRepositoryImpl
import de.dhbw.ka.storage.H2LentInstrumentStorage
import de.dhbw.ka.storage.H2MemberStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.borrowInstrument() {
    post("/lenting") {
        val receivedParams = call.receive<LentInstrumentDTO>()
        val lentInstrumentRepository: LentInstrumentRepository = LentInstrumentRepositoryImpl(lentInstrumentStorage = H2LentInstrumentStorage())
        val memberRepository: MemberRepository = MembersRepositoryImpl(memberStorage = H2MemberStorage())
        val borrowInstrumentUC = BorrowInstrument(lentInstrumentRepository = lentInstrumentRepository, memberRepository = memberRepository)
        borrowInstrumentUC.execute(receivedParams.memberId, InstrumentIdentification(receivedParams.instrumentIdentification.instrumentType,
        receivedParams.instrumentIdentification.instrumentSerialNumber,
        receivedParams.instrumentIdentification.instrumentManufacturer))
        call.respondText("Successfully borrowed an Instrument", status = HttpStatusCode.Created)
    }
}

fun Application.registerLentInstrumentController() {
    routing {
        borrowInstrument()
    }
}