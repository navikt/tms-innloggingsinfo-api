package no.nav.personbruker.tms.innloggingsinfo.api.common.exception

class UrlValidationException(message: String, cause: Throwable?) : Exception(message, cause) {

    val context: MutableMap<String, Any> = mutableMapOf()

    fun addContext(key: String, value: Any?) {
        context[key] = value.toString()
    }

    override fun toString(): String {
        return when (context.isNotEmpty()) {
            true -> super.toString() + ", context: $context"
            false -> super.toString()
        }
    }

}