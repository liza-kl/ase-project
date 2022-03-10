package de.dhbw.ka.controllers

import de.dhbw.ka.domain.entities.Instrument
import de.dhbw.ka.domain.repository.InstrumentRepository
import de.dhbw.ka.dto.InstrumentDTO
import de.dhbw.ka.dto.InstrumentDTO.InstrumentMapper.toInstrumentDTO
import de.dhbw.ka.instruments.CreateInstrument
import de.dhbw.ka.instruments.GetAllInstruments
import de.dhbw.ka.repository.InstrumentRepositoryImpl
import de.dhbw.ka.storage.H2InstrumentStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getInstrumentsRoute() {
    get("/instruments") {
        val instrumentRepository: InstrumentRepository =
            InstrumentRepositoryImpl(instrumentStorage = H2InstrumentStorage())
        val getAllInstrumentsUC = GetAllInstruments(instrumentRepository = instrumentRepository)
        val instruments: List<Instrument> = getAllInstrumentsUC.execute()
        val instrumentsResultList = mutableListOf<InstrumentDTO>()
        for (instrument in instruments) {
            val instrumentDTO = toInstrumentDTO(instrument)
            instrumentsResultList.add(instrumentDTO)
        }
        call.respond(instrumentsResultList)
    }
}

fun Route.addInstrument() {
    post("/instruments") {
        val receivedInstrumentParams = call.receive<InstrumentDTO>()
        val instrumentRepository: InstrumentRepository =
            InstrumentRepositoryImpl(instrumentStorage = H2InstrumentStorage())
        val createNewInstrumentUC = CreateInstrument(instrumentRepository = instrumentRepository)
        createNewInstrumentUC.execute(InstrumentDTO.toInstrument(receivedInstrumentParams))
        call.respondText(
            "Successfully created the instrumentIdentification ${receivedInstrumentParams.instrumentType}! ",
            status = HttpStatusCode.Created
        )
    }
}

fun Application.registerInstrumentController() {
    routing {
        getInstrumentsRoute()
        addInstrument()
    }
}
