package no.nav.personbruker.tms.innloggingsinfo.api.common.exception

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import no.nav.brukernotifikasjon.schemas.builders.exception.FieldValidationException
import org.slf4j.Logger


suspend fun respondWithError(call: ApplicationCall, log: Logger, exception: Exception) {
    when(exception) {
        is FieldValidationException -> {
            call.respond(HttpStatusCode.BadRequest)
            val msg = "Vi fikk en valideringsfeil. Returnerer feilkode. {}"
            log.error(msg, exception.toString(), exception)
        }
        else -> {
            call.respond(HttpStatusCode.InternalServerError)
            log.error("Ukjent feil oppstod. Returnerer feilkode til frontend", exception)
        }
    }
}