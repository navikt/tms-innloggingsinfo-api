package no.nav.personbruker.tms.innloggingsinfo.api.destinasjon

import no.nav.brukernotifikasjon.schemas.builders.util.ValidationUtil.validateMaxLength
import no.nav.personbruker.tms.innloggingsinfo.api.common.exception.UrlValidationException
import no.nav.personbruker.tms.innloggingsinfo.api.config.Environment

class DestinasjonsService(private val environment: Environment) {

    private val minInnboksPath = "/mininnboks"
    private val varselidPath = "/?varselid="

    fun getDestinationPath(type: String?, undertype: String?, varselid: String?): String {
        val validType = type?.let { validateMaxLength(type, "type", 50) }
        val validVarselid = varselid?.let { validateMaxLength(varselid, "varselid", 50) }

        return createDestinationPath(validType, validVarselid)
    }

    private fun createDestinationPath(type: String?, varselid: String?): String {
        var pathToDestination = "${environment.baseUrl}$minInnboksPath"

        if (type == "oppgave" || type == "dokument") {
            if (!varselid.isNullOrBlank()) {
                pathToDestination = "$pathToDestination$varselidPath$varselid"
            }
        }
        return pathToDestination
    }

}