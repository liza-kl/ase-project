package de.dhbw.ka.controllers

import de.dhbw.ka.domain.entities.Instrument
import de.dhbw.ka.domain.repository.InstrumentRepository
import de.dhbw.ka.dto.InstrumentDTO
import de.dhbw.ka.dto.InstrumentDTO.InstrumentMapper.toInstrument
import de.dhbw.ka.dto.InstrumentDTO.InstrumentMapper.toInstrumentDTO
import de.dhbw.ka.instruments.CreateInstrument
import de.dhbw.ka.instruments.GetAllInstruments
import de.dhbw.ka.repository.InstrumentRepositoryImpl
import de.dhbw.ka.storage.h2.H2InstrumentStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

internal object InstrumentProperties {
    val instrumentRepository: InstrumentRepository = InstrumentRepositoryImpl(instrumentStorage = H2InstrumentStorage())
}

fun Route.getInstruments() {
    get("/instruments") {
        val getAllInstrumentsUC = GetAllInstruments(instrumentRepository = InstrumentProperties.instrumentRepository)
        val instruments: List<Instrument> = getAllInstrumentsUC.execute()
        val instrumentsResultList = mutableListOf<InstrumentDTO>()
        instruments.map { instrumentsResultList.add(toInstrumentDTO(it)) }
        call.respond(instrumentsResultList)
    }
}

fun Route.addInstrument() {
    post("/instruments") {
        val receivedInstrumentParams = call.receive<InstrumentDTO>()
        val createNewInstrumentUC = CreateInstrument(instrumentRepository = InstrumentProperties.instrumentRepository)
        try {
            createNewInstrumentUC.execute(toInstrument(receivedInstrumentParams))
            call.respondText(
                "Successfully created the instrument with the id ${receivedInstrumentParams}! ",
                status = HttpStatusCode.Created
            )
        } catch (e: Exception) {
            call.respondText("Unfortunately something went wrong, ${e.message}")
        }

    }
}

fun Application.registerInstrumentController() {
    routing {
        getInstruments()
        addInstrument()
    }
}

