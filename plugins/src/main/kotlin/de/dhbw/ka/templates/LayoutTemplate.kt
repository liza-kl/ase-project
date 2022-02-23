package de.dhbw.ka.templates

import io.ktor.html.*
import kotlinx.html.*

class LayoutTemplate: Template<HTML> {
    val header = Placeholder<FlowContent>()
    val content = TemplatePlaceholder<ContentTemplate>()
    override fun HTML.apply() {
        head {
            meta(charset = "utf-8")
            meta(name = "viewport", content = "width=device-width, initial-scale=1, shrink-to-fit=no")
            title { +"Tag" }
            link(rel="stylesheet", href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css")
        }
        body {
            h1 {
                insert(header)
            }
            div("container vw-100 vh-100 d-flex justify-content-center align-items-center") {
                div("row") {
                    div("col-md-3")
                    div("col-12 col-md-6") {
                        h1 { + "Verwaltung Musikverein"}
                        insert(ContentTemplate(), content)}
                    div("col-md-3")
                }
            }
            script{src = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"}
        }
    }
}
