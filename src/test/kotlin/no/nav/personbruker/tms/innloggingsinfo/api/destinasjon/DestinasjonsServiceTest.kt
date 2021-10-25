package no.nav.personbruker.tms.innloggingsinfo.api.destinasjon

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import no.nav.brukernotifikasjon.schemas.builders.exception.FieldValidationException
import no.nav.personbruker.tms.innloggingsinfo.api.common.`with message containing`
import no.nav.personbruker.tms.innloggingsinfo.api.common.exception.UrlValidationException
import no.nav.personbruker.tms.innloggingsinfo.api.config.Environment
import org.amshove.kluent.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class DestinasjonsServiceTest {

    private val environment = mockk<Environment>()
    private val destinasjonsService = DestinasjonsService(environment)
    private val basePath = "dummyPath"
    private val undertype = "dummyUndertype"
    private val varselid = "dummyVarselid"

    @BeforeEach
    fun initMocks() {
        coEvery { environment.baseUrl } returns basePath
    }

    @Test
    fun `should return path with varselid when type is oppgave`() {
        val result = destinasjonsService.getDestinationPath(type = "oppgave", undertype, varselid)

        result `should contain` basePath
        result `should contain` varselid
    }

    @Test
    fun `should return path with varselid when type is dokument`() {
        val result = destinasjonsService.getDestinationPath(type = "dokument", undertype, varselid)

        result `should contain` basePath
        result `should contain` varselid
    }

    @Test
    fun `should return base path when type is url`() {
        val result = destinasjonsService.getDestinationPath(type = "url", undertype, varselid)

        result `should contain` basePath
        result `should not contain` varselid
    }

    @Test
    fun `should return base path when varselid is null`() {
        val result = destinasjonsService.getDestinationPath(type = "dokument", undertype, varselid = null)

        result `should contain` basePath
        result `should not contain` varselid
    }

    @Test
    fun `should return base path when varselid is empty`() {
        val result = destinasjonsService.getDestinationPath(type = "dokument", undertype, varselid = "")

        result `should contain` basePath
        result `should not contain` varselid
    }

    @Test
    fun `should throw exception if type is null`() {
        invoking {
            runBlocking {
                destinasjonsService.getDestinationPath(type = null, undertype, varselid)
            }
        } `should throw` UrlValidationException::class `with message containing` "Fant ingen url-config"
    }

    @Test
    fun `should throw exception if type is empty`() {
        invoking {
            runBlocking {
                destinasjonsService.getDestinationPath(type = "", undertype, varselid)
            }
        } `should throw` UrlValidationException::class `with message containing` "Fant ingen url-config"
    }

    @Test
    fun `should throw exception if type is not recognized`() {
        invoking {
            runBlocking {
                destinasjonsService.getDestinationPath(type = "notValidType", undertype, varselid)
            }
        } `should throw` UrlValidationException::class `with message containing` "Fant ingen url-config"
    }

    @Test
    fun `should throw exception if type is too long`() {
        val tooLongType = "T".repeat(51)
        invoking {
            runBlocking {
                destinasjonsService.getDestinationPath(tooLongType, undertype, varselid)
            }
        } `should throw` FieldValidationException::class
    }

    @Test
    fun `should throw exception if undertype is too long`() {
        val tooLongUndertype = "U".repeat(51)
        invoking {
            runBlocking {
                destinasjonsService.getDestinationPath(type = "oppgave", tooLongUndertype, varselid)
            }
        } `should throw` FieldValidationException::class
    }

    @Test
    fun `should throw exception if varselid is too long`() {
        val tooLongVarselid = "V".repeat(51)
        invoking {
            runBlocking {
                destinasjonsService.getDestinationPath(type = "oppgave", undertype, tooLongVarselid)
            }
        } `should throw` FieldValidationException::class
    }
}