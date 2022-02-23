package de.dhbw.ka

import de.dhbw.ka.templates.LayoutTemplate
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondHtmlTemplate(LayoutTemplate()) {
                content {
                    articleText {
                        +"Lorem Ipsum"
                    }
                }
            }
        }

    }

}
