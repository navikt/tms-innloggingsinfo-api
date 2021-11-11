package no.nav.personbruker.tms.innloggingsinfo.api.config

import no.nav.personbruker.tms.innloggingsinfo.api.destinasjon.DestinasjonService
import no.nav.personbruker.tms.innloggingsinfo.api.health.HealthService

class ApplicationContext {

    val environment = Environment()

    val httpClient = HttpClientBuilder.build()
    val healthService = HealthService(this)

    val destinasjonService = DestinasjonService(environment)

}
