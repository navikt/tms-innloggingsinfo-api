package no.nav.personbruker.tms.innloggingsinfo.api.config

import no.nav.personbruker.dittnav.common.util.config.StringEnvVar.getEnvVar

data class Environment(
    val corsAllowedOrigins: String = getEnvVar("CORS_ALLOWED_ORIGINS"),
    val corsAllowedSchemes: String = getEnvVar("CORS_ALLOWED_SCHEMES", "https"),
    val minInnboksPath: String = getEnvVar("MININNBOKS_PATH"),
    val minInnboksVarselidPath: String = getEnvVar("VARSELID_PATH")
)

