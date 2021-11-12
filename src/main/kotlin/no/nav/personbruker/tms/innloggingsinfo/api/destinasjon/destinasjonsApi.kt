package no.nav.personbruker.tms.innloggingsinfo.api.destinasjon

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import no.nav.personbruker.tms.innloggingsinfo.api.common.exception.respondWithError
import org.slf4j.LoggerFactory

fun Route.destinasjonApi(destinasjonsService: DestinasjonService) {

    val log = LoggerFactory.getLogger(DestinasjonService::class.java)

    get("/destinasjonsurl") {
        try {
            val url = destinasjonsService.getDestinationPath(
                call.request.typeParam,
                call.request.varselIdParam
            )

            call.respond(HttpStatusCode.OK, url)

        } catch (exception: Exception) {
            respondWithError(call, log, exception)
        }
    }
}

val ApplicationRequest.typeParam get() = queryParameters["type"]
val ApplicationRequest.varselIdParam get() = queryParameters["varselid"]
