package no.nav.personbruker.tms.innloggingsinfo.api.destinasjon

import no.nav.personbruker.tms.innloggingsinfo.api.config.Environment

class DestinasjonService(private val environment: Environment) {

    fun getDestinationPath(type: String?, varselid: String?): String {
        var pathToDestination = environment.minInnboksPath

        if (type == "oppgave" || type == "dokument") {
            if (!varselid.isNullOrBlank()) {
                pathToDestination = "$pathToDestination${environment.minInnboksVarselidPath}$varselid"
            }
        }
        return pathToDestination
    }
}
