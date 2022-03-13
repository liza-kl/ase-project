package de.dhbw.ka.controllers

import de.dhbw.ka.controllers.RentalInstrumentProperties.instrumentRepository
import de.dhbw.ka.controllers.RentalInstrumentProperties.rentalInstrumentRepository
import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.repository.InstrumentRepository
import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.dto.RentalInstrumentDTO
import de.dhbw.ka.dto.RentalInstrumentDTO.RentalInstrumentMapper.toRentalInstrument
import de.dhbw.ka.dto.RentalInstrumentDTO.RentalInstrumentMapper.toRentalInstrumentDTO
import de.dhbw.ka.rentalinstruments.CreateRentalInstrument
import de.dhbw.ka.rentalinstruments.GetAllRentalInstruments
import de.dhbw.ka.repository.InstrumentRepositoryImpl
import de.dhbw.ka.repository.RentalInstrumentRepositoryImpl
import de.dhbw.ka.storage.h2.H2InstrumentStorage
import de.dhbw.ka.storage.h2.H2RentalInstrumentStorage
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

internal object RentalInstrumentProperties {
    val rentalInstrumentRepository: RentalInstrumentRepository =
        RentalInstrumentRepositoryImpl(rentalInstrumentStorage = H2RentalInstrumentStorage())
    val instrumentRepository: InstrumentRepository = InstrumentRepositoryImpl(instrumentStorage = H2InstrumentStorage())
}

fun Route.getAllRentalInstruments() {
    get("/rentalinstruments") {
        val getAllRentalInstruments = GetAllRentalInstruments(rentalInstrumentRepository)
        val rentalInstruments: List<RentalInstrument> = getAllRentalInstruments.execute()
        val rentalInstrumentsResultsList = mutableListOf<RentalInstrumentDTO>()
        rentalInstruments.map { rentalInstrumentsResultsList.add(toRentalInstrumentDTO(it)) }
        call.respond(rentalInstrumentsResultsList)
    }
}

fun Route.createRentalInstrument() {
    post("/rentalinstruments") {
        val rentalInstrumentParams = call.receive<RentalInstrumentDTO>()
        val createRentalInstrumentUC = CreateRentalInstrument(rentalInstrumentRepository, instrumentRepository)
        createRentalInstrumentUC.execute(toRentalInstrument(rentalInstrumentParams))
        call.respondText { "Successfully created a new rental instrument" }
    }
}

fun Application.registerRentalInstrumentsController() {
    routing {
        getAllRentalInstruments()
        createRentalInstrument()
    }
}
