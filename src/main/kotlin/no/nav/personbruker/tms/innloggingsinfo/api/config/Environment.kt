package no.nav.personbruker.tms.innloggingsinfo.api.config

data class Environment(
    val corsAllowedOrigins: String = getEnvVar("CORS_ALLOWED_ORIGINS"),
    val baseUrl: String = getEnvVar("BASE_URL")
)

fun getEnvVar(varName: String): String {
    return System.getenv(varName)
        ?: throw IllegalArgumentException("Appen kan ikke starte uten av miljøvariabelen $varName er satt.")
}
