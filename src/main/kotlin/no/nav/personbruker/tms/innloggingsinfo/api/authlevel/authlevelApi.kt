package no.nav.personbruker.tms.innloggingsinfo.api.authlevel

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import no.nav.personbruker.tms.innloggingsinfo.api.common.exception.respondWithError
import no.nav.personbruker.tms.innloggingsinfo.api.config.authenticatedUser
import no.nav.personbruker.tms.innloggingsinfo.api.destinasjon.DestinasjonService
import org.slf4j.LoggerFactory

fun Route.authlevelApi() {

    val log = LoggerFactory.getLogger(DestinasjonService::class.java)

    get("/authlevel") {
        try {
            val authlevel = authenticatedUser.loginLevel
            call.respond(HttpStatusCode.OK, authlevel)

        } catch (exception: Exception) {
            respondWithError(call, log, exception)
        }
    }
}
