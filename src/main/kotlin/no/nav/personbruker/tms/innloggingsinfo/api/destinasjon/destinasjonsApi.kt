package no.nav.personbruker.tms.innloggingsinfo.api.destinasjon

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import no.nav.personbruker.tms.innloggingsinfo.api.common.exception.respondWithError
import org.slf4j.LoggerFactory

fun Route.destinasjonsApi(destinasjonsService: DestinasjonsService) {

    val log = LoggerFactory.getLogger(DestinasjonsService::class.java)

    get("/destinasjonsurl") {
        try {
            val url =
                    destinasjonsService.getDestinationPath(
                            call.request.queryParameters["type"],
                            call.request.queryParameters["undertype"],
                            call.request.queryParameters["varselid"])
            call.respond(HttpStatusCode.OK, url)

        } catch (exception: Exception) {
            respondWithError(call, log, exception)
        }
    }
}