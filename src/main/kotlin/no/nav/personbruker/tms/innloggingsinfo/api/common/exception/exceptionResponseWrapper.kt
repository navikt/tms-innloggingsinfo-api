package no.nav.personbruker.tms.innloggingsinfo.api.common.exception

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import org.slf4j.Logger

suspend fun respondWithError(call: ApplicationCall, log: Logger, exception: Exception) {
    call.respond(HttpStatusCode.InternalServerError)
    log.error("Ukjent feil oppstod. Returnerer feilkode til frontend", exception)
}
