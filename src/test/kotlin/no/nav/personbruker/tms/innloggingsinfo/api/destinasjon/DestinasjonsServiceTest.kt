package no.nav.personbruker.tms.innloggingsinfo.api.destinasjon

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import no.nav.brukernotifikasjon.schemas.builders.exception.FieldValidationException
import no.nav.personbruker.tms.innloggingsinfo.api.config.Environment
import org.amshove.kluent.`should contain`
import org.amshove.kluent.`should not contain`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class DestinasjonsServiceTest {

    private val environment = mockk<Environment>()
    private val destinasjonsService = DestinasjonService(environment)
    private val minInnboksPath = "dummyPath_mininnboks"
    private val varselidPath = "dummyPath_varsel"
    private val varselid = "dummyVarselid"

    @BeforeEach
    fun initMocks() {
        coEvery { environment.minInnboksPath } returns minInnboksPath
        coEvery { environment.minInnboksVarselidPath } returns varselidPath
    }

    @Test
    fun `should return path with varselid when type is oppgave`() {
        val result = destinasjonsService.getDestinationPath(type = "oppgave", varselid)

        result `should contain` minInnboksPath
        result `should contain` varselidPath
        result `should contain` varselid
    }

    @Test
    fun `should return path with varselid when type is dokument`() {
        val result = destinasjonsService.getDestinationPath(type = "dokument", varselid)

        result `should contain` minInnboksPath
        result `should contain` varselidPath
        result `should contain` varselid
    }

    @Test
    fun `should return base path when varselid is null and type is dokument`() {
        val result = destinasjonsService.getDestinationPath(type = "dokument", varselid = null)

        result `should contain` minInnboksPath
        result `should not contain` varselidPath
        result `should not contain` varselid
    }

    @Test
    fun `should return base path when varselid is null and type is oppgave`() {
        val result = destinasjonsService.getDestinationPath(type = "oppgave", varselid = null)

        result `should contain` minInnboksPath
        result `should not contain` varselidPath
        result `should not contain` varselid
    }

    @Test
    fun `should return base path when varselid is empty and type is dokument`() {
        val result = destinasjonsService.getDestinationPath(type = "dokument", varselid = "")

        result `should contain` minInnboksPath
        result `should not contain` varselidPath
        result `should not contain` varselid
    }


    @Test
    fun `should return base path when varselid is empty and type is oppgave`() {
        val result = destinasjonsService.getDestinationPath(type = "oppgave", varselid = "")

        result `should contain` minInnboksPath
        result `should not contain` varselidPath
        result `should not contain` varselid
    }

    @Test
    fun `should return base path when type is null`() {
        val result = destinasjonsService.getDestinationPath(type = null, varselid)

        result `should contain` minInnboksPath
        result `should not contain` varselidPath
        result `should not contain` varselid
    }

    @Test
    fun `should return base path when type is empty`() {
        val result = destinasjonsService.getDestinationPath(type = "", varselid)

        result `should contain` minInnboksPath
        result `should not contain` varselidPath
        result `should not contain` varselid
    }

    @Test
    fun `should return base path when type is not recognized`() {
        val result = destinasjonsService.getDestinationPath(type = "notValidType", varselid)

        result `should contain` minInnboksPath
        result `should not contain` varselidPath
        result `should not contain` varselid
    }

    @Test
    fun `should throw exception if type is too long`() {
        val tooLongType = "T".repeat(51)
        invoking {
            runBlocking {
                destinasjonsService.getDestinationPath(tooLongType, varselid)
            }
        } `should throw` FieldValidationException::class
    }

    @Test
    fun `should throw exception if varselid is too long`() {
        val tooLongVarselid = "V".repeat(51)
        invoking {
            runBlocking {
                destinasjonsService.getDestinationPath(type = "oppgave", tooLongVarselid)
            }
        } `should throw` FieldValidationException::class
    }
}
