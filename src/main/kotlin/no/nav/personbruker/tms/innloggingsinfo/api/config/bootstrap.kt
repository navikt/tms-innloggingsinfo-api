package no.nav.personbruker.tms.innloggingsinfo.api.config

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.client.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import io.prometheus.client.hotspot.DefaultExports
import no.nav.personbruker.dittnav.common.security.AuthenticatedUser
import no.nav.personbruker.tms.innloggingsinfo.api.authlevel.authlevelApi
import no.nav.personbruker.tms.innloggingsinfo.api.destinasjon.destinasjonsApi
import no.nav.personbruker.tms.innloggingsinfo.api.health.healthApi
import no.nav.security.token.support.ktor.tokenValidationSupport
import org.slf4j.LoggerFactory
import java.time.Instant

val log = LoggerFactory.getLogger(ApplicationContext::class.java)

@KtorExperimentalAPI
fun Application.mainModule(appContext: ApplicationContext = ApplicationContext()) {

    DefaultExports.initialize()

    install(DefaultHeaders)

    install(CORS) {
        host(appContext.environment.corsAllowedOrigins, schemes = listOf(appContext.environment.corsAllowedSchemes))
        allowCredentials = true
        header(HttpHeaders.ContentType)
    }

    val config = this.environment.config

    install(ContentNegotiation) {
        jackson {
            enableDittNavJsonConfig()
        }
    }

    install(Authentication) {
        tokenValidationSupport(config = config)
    }

    routing {
        healthApi(appContext.healthService)

        authenticate {
            authlevelApi()
            destinasjonsApi(appContext.destinasjonsService)
        }
    }

    configureShutdownHook(appContext.httpClient)
}

private fun Application.configureShutdownHook(httpClient: HttpClient) {
    environment.monitor.subscribe(ApplicationStopping) {
        httpClient.close()
    }
}

val PipelineContext<Unit, ApplicationCall>.authenticatedUser: AuthenticatedUser
    get() = no.nav.personbruker.dittnav.common.security.AuthenticatedUserFactory.createNewAuthenticatedUser(call)

suspend fun PipelineContext<Unit, ApplicationCall>.executeOnUnexpiredTokensOnly(block: suspend () -> Unit) {
    if (authenticatedUser.isTokenExpired()) {
        val delta = authenticatedUser.tokenExpirationTime.epochSecond - Instant.now().epochSecond
        val msg = "Mottok kall fra en bruker med et utløpt token, avviser request-en med en 401-respons. " +
                "Tid siden tokenet løp ut: $delta sekunder, $authenticatedUser"
        log.info(msg)
        call.respond(HttpStatusCode.Unauthorized)

    } else {
        block.invoke()
    }
}
