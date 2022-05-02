package no.nav.personbruker.tms.innloggingsinfo.api.redirect

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val log: Logger = LoggerFactory.getLogger("redirectApi")

fun Route.redirectApi(infoPageUri: String) {
    get("/innloggingsinfo/*") {
        val type = call.typeParam
        val undertype = call.undertypeParam
        val varselid = call.varselidParam
        val henvendelsesid = call.henvendelsesidParam

        log.info("Sending redirect for path [${call.request.local.uri}], params {type: $type, undertype: $undertype, varselId: $varselid, henvendelsesId: $henvendelsesid}")

        call.respondRedirect(infoPageUri)
    }
}

val ApplicationCall.typeParam get() = request.queryParameters["type"]
val ApplicationCall.undertypeParam get() = request.queryParameters["undertype"]
val ApplicationCall.varselidParam get() = request.queryParameters["varselid"]
val ApplicationCall.henvendelsesidParam get() = request.queryParameters["henvendelsesid"]
