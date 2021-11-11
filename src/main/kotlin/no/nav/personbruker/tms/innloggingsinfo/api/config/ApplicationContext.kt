package no.nav.personbruker.tms.innloggingsinfo.api.config

import no.nav.personbruker.tms.innloggingsinfo.api.destinasjon.DestinasjonsService
import no.nav.personbruker.tms.innloggingsinfo.api.health.HealthService

class ApplicationContext {

    val environment = Environment()

    val httpClient = HttpClientBuilder.build()
    val healthService = HealthService(this)

    val destinasjonsService = DestinasjonsService(environment)

}
