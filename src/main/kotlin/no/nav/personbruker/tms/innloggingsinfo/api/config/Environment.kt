package no.nav.personbruker.tms.innloggingsinfo.api.config

import no.nav.personbruker.dittnav.common.util.config.StringEnvVar.getEnvVar

data class Environment(
    val infoPageUrl: String = getEnvVar("MININNBOKS_INFO_PAGE_URL")
)

