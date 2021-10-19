package no.nav.personbruker.tms.innloggingsinfo.api.health

import no.nav.personbruker.tms.innloggingsinfo.api.config.ApplicationContext

class HealthService(private val applicationContext: ApplicationContext) {

    suspend fun getHealthChecks(): List<HealthStatus> {
        return emptyList()
    }
}
