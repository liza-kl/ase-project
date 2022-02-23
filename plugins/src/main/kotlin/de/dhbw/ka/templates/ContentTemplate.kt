package de.dhbw.ka.templates

import io.ktor.html.*
import kotlinx.html.FlowContent
import kotlinx.html.article
import kotlinx.html.p

class ContentTemplate: Template<FlowContent> {
    val articleText = Placeholder<FlowContent>()
    override fun FlowContent.apply() {
        article {
            p {
                insert(articleText)
            }
        }
    }
}
