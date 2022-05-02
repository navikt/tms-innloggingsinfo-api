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
    val type = typeParam
    val undertype = undertypeParam
    val varselid = varselidParam
    val henvendelsesid = henvendelsesidParam

    log.info("Sending redirect for path [${request.local.uri}], params {type: $type, undertype: $undertype, varselId: $varselid, henvendelsesId: $henvendelsesid}")

    respondRedirect(redirectUri)
}

val ApplicationCall.typeParam get() = request.queryParameters["type"]
val ApplicationCall.undertypeParam get() = request.queryParameters["undertype"]
val ApplicationCall.varselidParam get() = request.queryParameters["varselid"]
val ApplicationCall.henvendelsesidParam get() = request.queryParameters["henvendelsesid"]
