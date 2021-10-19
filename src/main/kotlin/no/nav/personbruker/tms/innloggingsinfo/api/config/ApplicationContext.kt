package no.nav.personbruker.tms.innloggingsinfo.api.config

import no.nav.personbruker.tms.innloggingsinfo.api.health.HealthService

class ApplicationContext {

    val httpClient = HttpClientBuilder.build()
    val healthService = HealthService(this)

}
