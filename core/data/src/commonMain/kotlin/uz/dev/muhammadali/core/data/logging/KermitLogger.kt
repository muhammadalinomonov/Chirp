package uz.dev.muhammadali.core.data.logging

import co.touchlab.kermit.Logger
import uz.dev.muhammadali.domain.domain.logging.ChirpLogger

object KermitLogger : ChirpLogger {
    override fun debug(message: String) {
        Logger.d(message)
    }

    override fun info(message: String) {
        Logger.i(message)
    }

    override fun warning(message: String) {
        Logger.w(message)
    }

    override fun error(message: String, throwable: Throwable?) {
        Logger.e(message, throwable)
    }
}