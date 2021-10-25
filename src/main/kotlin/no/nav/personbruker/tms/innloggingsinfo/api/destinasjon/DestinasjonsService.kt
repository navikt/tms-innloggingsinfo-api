package no.nav.personbruker.tms.innloggingsinfo.api.destinasjon

import no.nav.brukernotifikasjon.schemas.builders.util.ValidationUtil.validateMaxLength
import no.nav.personbruker.tms.innloggingsinfo.api.common.exception.UrlValidationException
import no.nav.personbruker.tms.innloggingsinfo.api.config.Environment

class DestinasjonsService(private val environment: Environment) {

    private val minInnboksPath = "/mininnboks"
    private val varselidPath = "/?varselid="
    private val allValidTypes = listOf("oppgave", "dokument", "url")
    private val typesToUseWithVarselidPath = listOf("oppgave", "dokument")

    fun getDestinationPath(type: String?, undertype: String?, varselid: String?): String {
        val validType = type?.let { validateMaxLength(type, "type", 50) }
        val validUndertype = undertype?.let { validateMaxLength(undertype, "undertype", 50) }
        val validVarselid = varselid?.let { validateMaxLength(varselid, "varselid", 50) }

        return createDestinationPath(validType, validUndertype, validVarselid)
    }

    private fun createDestinationPath(type: String?, undertype: String?, varselid: String?): String {
        var pathToDestination = "${environment.baseUrl}$minInnboksPath"

        if (!allValidTypes.contains(type)) {
            val exception = UrlValidationException("Fant ingen url-config.", RuntimeException())
            exception.addContext("type", type)
            exception.addContext("undertype", undertype)
            exception.addContext("varselid", varselid)
            throw exception
        }

        if (typesToUseWithVarselidPath.contains(type)) {
            if (!varselid.isNullOrBlank()) {
                pathToDestination = "$pathToDestination$varselidPath$varselid"
            }
        }

        return pathToDestination
    }

}