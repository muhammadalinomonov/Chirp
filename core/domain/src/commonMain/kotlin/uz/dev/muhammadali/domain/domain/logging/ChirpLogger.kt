package uz.dev.muhammadali.domain.domain.logging

interface ChirpLogger {
    fun debug(message: String)
    fun info(message: String)
    fun warning(message: String)
    fun error(message: String, throwable: Throwable? = null)
}