package no.nav.personbruker.tms.innloggingsinfo.api.health

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import kotlinx.coroutines.coroutineScope
import kotlinx.html.*

suspend fun ApplicationCall.buildSelftestPage() = coroutineScope {

    respondHtml(status = HttpStatusCode.OK)
    {
        head {
            title { +"Selftest tms-innloggingsinfo-api" }
        }
        body {
            h1 {
                style =  "background: green"
                +"Service-status: OK"
            }
        }
    }
}
