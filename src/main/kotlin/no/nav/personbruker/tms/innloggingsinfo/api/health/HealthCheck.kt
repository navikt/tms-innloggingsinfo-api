package no.nav.personbruker.tms.innloggingsinfo.api.health

interface HealthCheck {

    suspend fun status(): HealthStatus

}
