package no.nav.personbruker.tms.innloggingsinfo.api.destinasjon

import no.nav.brukernotifikasjon.schemas.builders.util.ValidationUtil.validateNonNullFieldMaxLength
import org.slf4j.LoggerFactory
import java.util.*
import java.util.stream.Stream

class DestinasjonsService {

    private val log = LoggerFactory.getLogger(DestinasjonsService::class.java)

    fun hentDestinasjonsUrl(type: String?, undertype: String?, varselid: String?): String {
        val validType = validateNonNullFieldMaxLength(type, "type", 100)
        val validUndertype = validateNonNullFieldMaxLength(undertype, "undertype", 100)
        val validVarselid = validateNonNullFieldMaxLength(varselid, "varselid", 100)

        /*
        //Opprett url fra input fra front-end. Fra innloggingsinfo-api:
        val urlPatterns = getUrlPatterns()
        val url = lagDestinasjonsurl(urlPatterns, validType, validUndertype, validVarselid)
        return url
         */
        return ""
    }


    /*
    //fra innloggingsinfo-api:

    private fun getUrlPatterns(): Map<String, String> {
        val patterns: MutableMap<String, String> = HashMap()
        patterns["url"] = "/mininnboks"
        patterns["oppgave.url"] = "/mininnboks/?varselid={varselid}"
        patterns["dokument.url"] = "/mininnboks/?varselid={varselid}"
        return patterns
    }

    fun lagDestinasjonsurl(urlPatterns: Map<String, String>, type: String, undertype: String, varselid: String): String {
        var urlConfig = finnUrlConfig(type, undertype, urlPatterns)
        urlConfig = if (varselid.isNotEmpty()) {
            urlConfig!!.replace("{varselid}", varselid!!)
        } else {
            urlConfig!!.replace("/?varselid={varselid}", "")
        }
        return urlConfig
    }

    private fun finnUrlConfig(type: String, undertype: String, urlPatterns: Map<String, String>): String? {
        return Stream.of(String.format("%s.%s.url", type, undertype), String.format("%s.url", type), "url")
                .filter { key: String -> urlPatterns.containsKey(key) }
                .map { key: String -> urlPatterns[key] }
                .findFirst().orElseThrow { RuntimeException("Fant ingen url-config") }
    }
     */

}