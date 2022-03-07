package de.dhbw.ka.controllers

import de.dhbw.ka.domain.repository.InstrumentRepository
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.dto.InstrumentDTO
import de.dhbw.ka.dto.MemberDTO
import de.dhbw.ka.instruments.CreateInstrument
import de.dhbw.ka.members.CreateNewMember
import de.dhbw.ka.repository.InstrumentRepositoryImpl
import de.dhbw.ka.repository.MembersRepositoryImpl
import de.dhbw.ka.storage.H2InstrumentStorage
import de.dhbw.ka.storage.H2MemberStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getInstrumentsRoute() {
    get("/instruments") {
        call.respondText("Instruments")
    }
}

fun Route.addInstrument() {
    post("/instruments") {
        val receivedInstrumentParams = call.receive<InstrumentDTO>()
        val instrumentRepository: InstrumentRepository = InstrumentRepositoryImpl(instrumentStorage = H2InstrumentStorage())
        val createNewInstrumentUC = CreateInstrument(instrumentRepository = instrumentRepository)
        createNewInstrumentUC.execute(InstrumentDTO.toInstrument(receivedInstrumentParams))
        call.respondText("Successfully created the instrument ${receivedInstrumentParams.instrumentType}! ", status = HttpStatusCode.Created)
    }
}

fun Application.registerInstrumentController() {
    routing {
        getInstrumentsRoute()
        addInstrument()
    }
}

