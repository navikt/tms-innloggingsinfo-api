package no.nav.personbruker.tms.innloggingsinfo.api.destinasjon

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import no.nav.brukernotifikasjon.schemas.builders.exception.FieldValidationException
import no.nav.personbruker.tms.innloggingsinfo.api.common.`with message containing`
import no.nav.personbruker.tms.innloggingsinfo.api.common.exception.UrlValidationException
import no.nav.personbruker.tms.innloggingsinfo.api.config.Environment
import org.amshove.kluent.`should contain`
import org.amshove.kluent.`should not contain`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
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
    fun `should return base path when varselid is null and type is dokument`() {
        val result = destinasjonsService.getDestinationPath(type = "dokument", undertype, varselid = null)

        result `should contain` basePath
        result `should not contain` varselid
    }

    @Test
    fun `should return base path when varselid is null and type is oppgave`() {
        val result = destinasjonsService.getDestinationPath(type = "oppgave", undertype, varselid = null)

        result `should contain` basePath
        result `should not contain` varselid
    }

    @Test
    fun `should return base path when varselid is empty and type is dokument`() {
        val result = destinasjonsService.getDestinationPath(type = "dokument", undertype, varselid = "")

        result `should contain` basePath
        result `should not contain` varselid
    }


    @Test
    fun `should return base path when varselid is empty and type is oppgave`() {
        val result = destinasjonsService.getDestinationPath(type = "oppgave", undertype, varselid = "")

        result `should contain` basePath
        result `should not contain` varselid
    }

    @Test
    fun `should return base path when type is null`() {
        val result = destinasjonsService.getDestinationPath(type = null, undertype, varselid)

        result `should contain` basePath
        result `should not contain` varselid
    }

    @Test
    fun `should return base path when type is empty`() {
        val result = destinasjonsService.getDestinationPath(type = "", undertype, varselid)

        result `should contain` basePath
        result `should not contain` varselid
    }

    @Test
    fun `should return base path when type is not recognized`() {
        val result = destinasjonsService.getDestinationPath(type = "notValidType", undertype, varselid)

        result `should contain` basePath
        result `should not contain` varselid
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
    fun `should throw exception if varselid is too long`() {
        val tooLongVarselid = "V".repeat(51)
        invoking {
            runBlocking {
                destinasjonsService.getDestinationPath(type = "oppgave", undertype, tooLongVarselid)
            }
        } `should throw` FieldValidationException::class
    }
}