package no.nav.personbruker.tms.innloggingsinfo.api.health

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.healthApi() {

    get("/tms-innloggingsinfo-api/internal/isAlive") {
        call.respondText(text = "ALIVE", contentType = ContentType.Text.Plain)
    }

    get("/tms-innloggingsinfo-api/internal/isReady") {
        call.respondText(text = "READY", contentType = ContentType.Text.Plain)
    }

    get("/tms-innloggingsinfo-api/internal/selftest") {
        call.buildSelftestPage()
    }
}
