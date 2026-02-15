package uz.dev.muhammadali.core.data.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import uz.dev.muhammadali.core.data.mappers.toSerializable
import uz.dev.muhammadali.domain.domain.auth.AuthInfo
import uz.dev.muhammadali.domain.domain.auth.SessionStorage

class DataStoreSessionStorage(
    private val dataStore: DataStore<Preferences>,
) : SessionStorage {
    private val authInfoKey = stringPreferencesKey("KEY_AUTH_INFO")

    private val json = Json {
        ignoreUnknownKeys = true

    }

    override fun observeAuthInfo(): Flow<AuthInfo?> {
        return dataStore.data.map { prefs ->
            val serializedJson = prefs[authInfoKey]
            serializedJson?.let {
                json.decodeFromString(it)
            }
        }
    }

    override suspend fun set(authInfo: AuthInfo?) {
        if (authInfo == null) {
            dataStore.edit {
                it.remove(authInfoKey)
            }

            return
        }
        val serialized = json.encodeToString(authInfo.toSerializable())

        dataStore.edit { prefs ->
            prefs[authInfoKey] = serialized
        }
    }
}