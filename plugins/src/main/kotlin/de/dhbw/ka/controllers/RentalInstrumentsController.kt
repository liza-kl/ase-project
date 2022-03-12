package de.dhbw.ka.controllers

import de.dhbw.ka.domain.repository.InstrumentRentalRepository
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.dto.RentalInstrumentDTO
import de.dhbw.ka.instrumentrental.BorrowInstrument
import de.dhbw.ka.instrumentrental.GetAllInstrumentRentals
import de.dhbw.ka.repository.InstrumentRentalRepositoryImpl
import de.dhbw.ka.repository.MembersRepositoryImpl
import de.dhbw.ka.storage.h2.H2InstrumentRentalStorage
import de.dhbw.ka.storage.h2.H2MemberStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.borrowInstrument() {
    post("/rental") {
        val receivedParams = call.receive<RentalInstrumentDTO>()
        val instrumentRentalRepository: InstrumentRentalRepository =
            InstrumentRentalRepositoryImpl(instrumentRentalStorage = H2InstrumentRentalStorage())
        val memberRepository: MemberRepository = MembersRepositoryImpl(memberStorage = H2MemberStorage())
        val borrowInstrumentUC =
            BorrowInstrument(instrumentRentalRepository = instrumentRentalRepository, memberRepository = memberRepository)
        borrowInstrumentUC.execute(
            receivedParams.memberId,
            InstrumentIdentification(
                receivedParams.instrumentIdentification.instrumentType,
                receivedParams.instrumentIdentification.instrumentSerialNumber,
                receivedParams.instrumentIdentification.instrumentManufacturer
            )
        )
        call.respondText("Successfully borrowed an Instrument", status = HttpStatusCode.Created)
    }
}

fun Route.getAllRentedInstruments() {

    get("/rental") {
        val instrumentRentalRepository: InstrumentRentalRepository =
            InstrumentRentalRepositoryImpl(instrumentRentalStorage = H2InstrumentRentalStorage())
        val getAllInstrumentRentalsUC = GetAllInstrumentRentals(instrumentRentalRepository = instrumentRentalRepository)
        val lentInstrumentsList = getAllInstrumentRentalsUC.execute()
        val lentInstrumentsResultList = mutableListOf<RentalInstrumentDTO>()
        lentInstrumentsList.map {
            lentInstrumentsResultList.add(RentalInstrumentDTO.toRentalInstrumentDTO(it))
        }
        call.respond(lentInstrumentsResultList)
    }
}

fun Application.registerRentalInstrumentsController() {
    routing {
        getAllRentedInstruments()
        borrowInstrument()
    }
}