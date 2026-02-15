package uz.dev.muhammadali.core.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module
import uz.dev.muhammadali.core.data.auth.createDataStore

actual val platformCoreDataModule = module {

    single<HttpClientEngine> {
        Darwin.create()
    }

    single<DataStore<Preferences>> {
        createDataStore()
    }
}