package uz.dev.muhammadali.chrip

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import uz.dev.muhammadali.chrip.di.initKoin

class ChirpApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@ChirpApplication)
            androidLogger()
        }
    }
}
