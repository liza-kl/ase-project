package de.dhbw.ka.controllers

import de.dhbw.ka.controllers.RentalInstrumentEntriesProperties.instrumentRentalEntryRepository
import de.dhbw.ka.controllers.RentalInstrumentEntriesProperties.memberRepository
import de.dhbw.ka.controllers.RentalInstrumentEntriesProperties.rentalInstrumentRepository
import de.dhbw.ka.domain.repository.InstrumentRentalEntryRepository
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.dto.RentalInstrumentEntryDTO
import de.dhbw.ka.instrumentrental.GetAllInstrumentRentalEntries
import de.dhbw.ka.instrumentrental.RentInstrument
import de.dhbw.ka.repository.InstrumentRentalEntryRepositoryImpl
import de.dhbw.ka.repository.MembersRepositoryImpl
import de.dhbw.ka.repository.RentalInstrumentRepositoryImpl
import de.dhbw.ka.storage.h2.H2InstrumentRentalEntryStorage
import de.dhbw.ka.storage.h2.H2MemberStorage
import de.dhbw.ka.storage.h2.H2RentalInstrumentStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

internal object RentalInstrumentEntriesProperties {
    val instrumentRentalEntryRepository: InstrumentRentalEntryRepository =
        InstrumentRentalEntryRepositoryImpl(instrumentRentalEntryStorage = H2InstrumentRentalEntryStorage())
    val memberRepository: MemberRepository = MembersRepositoryImpl(memberStorage = H2MemberStorage())
    val rentalInstrumentRepository: RentalInstrumentRepository =
        RentalInstrumentRepositoryImpl(rentalInstrumentStorage = H2RentalInstrumentStorage())
}

fun Route.borrowInstrument() {
    post("/rental") {
        val receivedParams = call.receive<RentalInstrumentEntryDTO>()
        val rentInstrumentUC =
            RentInstrument(
                instrumentRentalEntryRepository,
                rentalInstrumentRepository,
                memberRepository
            )
        rentInstrumentUC.execute(
            receivedParams.memberId,
            InstrumentIdentification(
                instrumentManufacturer = receivedParams.instrumentIdentification.instrumentManufacturer,
                instrumentSerialNumber = receivedParams.instrumentIdentification.instrumentSerialNumber,
                instrumentType = receivedParams.instrumentIdentification.instrumentType
            )
        )
        call.respondText("Successfully borrowed an Instrument", status = HttpStatusCode.Created)
    }
}

fun Route.getAllRentedInstrumentEntries() {

    get("/rental") {
        val getAllInstrumentRentalEntriesUC =
            GetAllInstrumentRentalEntries(instrumentRentalEntryRepository)
        val lentInstrumentsList = getAllInstrumentRentalEntriesUC.execute()
        val lentInstrumentsResultList = mutableListOf<RentalInstrumentEntryDTO>()
        lentInstrumentsList.map {
            lentInstrumentsResultList.add(RentalInstrumentEntryDTO.toRentalInstrumentEntryDTO(it))
        }
        call.respond(lentInstrumentsResultList)
    }
}

fun Application.registerRentalInstrumentEntriesController() {
    routing {
        getAllRentedInstrumentEntries()
        borrowInstrument()
    }
}