package de.dhbw.ka.controllers

import de.dhbw.ka.domain.entities.Instrument
import de.dhbw.ka.domain.repository.InstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentCategory
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.server.module
import io.kotest.assertions.ktor.shouldHaveContent
import io.kotest.assertions.ktor.shouldHaveStatus
import io.kotest.assertions.ktor.shouldNotHaveContent
import io.kotest.core.spec.style.FunSpec
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.every
import io.mockk.mockk

/**
 * Used Testing Library: kotest.io
 * Used Mocking Library: mockk.io
 */

class ControllerTests : FunSpec({

    val instrumentRepository = mockk<InstrumentRepository>(relaxed = true)

    test("Should get all Instruments") {
        every { instrumentRepository.getAllInstruments() } returns listOf(
            Instrument(
                InstrumentIdentification("Yamaha", "YHR-567D", "French Horn"),
                InstrumentCategory("BRASS"),
            )
        )
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/instruments").apply {
                response shouldHaveStatus HttpStatusCode.OK
                response shouldNotHaveContent "failure"
                response shouldHaveContent "[{\"instrumentType\":\"French Horn\"," +
                        "\"instrumentManufacturer\":\"Yamaha\"," +
                        "\"instrumentSerialNumber\":\"YHR-567D\"," +
                        "\"instrumentCategory\":\"BRASS\"}]"
            }
        }
    }

})
