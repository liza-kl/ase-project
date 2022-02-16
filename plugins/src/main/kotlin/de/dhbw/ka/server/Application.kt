package de.dhbw.ka.server


import de.dhbw.ka.configureRouting
import de.dhbw.ka.routes.getInstrumentsRoute
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing : Boolean = false) {
        configureRouting()
        getInstrumentsRoute()
    }

