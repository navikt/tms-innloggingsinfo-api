package no.nav.personbruker.tms.innloggingsinfo.api.config

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import no.nav.personbruker.tms.innloggingsinfo.api.health.healthApi
import no.nav.personbruker.tms.innloggingsinfo.api.redirect.redirectApi

fun Application.mainModule(env: Environment = Environment()) {

    install(DefaultHeaders)

    routing {
        healthApi()
        redirectApi(env.infoPageUrl)
    }
}
