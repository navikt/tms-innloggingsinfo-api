package no.nav.personbruker.tms.innloggingsinfo.api.redirect

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val log: Logger = LoggerFactory.getLogger("redirectApi")

fun Route.redirectApi(infoPageUri: String) {
    get("/innloggingsinfo/{...}") {
        call.logInfoAndSendRedirect(infoPageUri)
    }

    get("/innloggingsinfo") {
        call.logInfoAndSendRedirect(infoPageUri)
    }
}

private suspend fun ApplicationCall.logInfoAndSendRedirect(redirectUri: String) {
    log.info("Sending redirect for path [${request.local.uri}], request {referer: ${referer}, origin: $origin}")

    respondRedirect(redirectUri)
}

val ApplicationCall.referer get() = request.headers["referer"]
val ApplicationCall.origin get() = request.headers["origin"]
