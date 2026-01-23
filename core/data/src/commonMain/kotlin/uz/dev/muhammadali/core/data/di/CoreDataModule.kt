package uz.dev.muhammadali.core.data.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import uz.dev.muhammadali.core.data.auth.KtorAuthService
import uz.dev.muhammadali.core.data.logging.KermitLogger
import uz.dev.muhammadali.core.data.networking.HttpClientFactory
import uz.dev.muhammadali.domain.domain.auth.AuthService
import uz.dev.muhammadali.domain.domain.logging.ChirpLogger

expect val platformCoreDataModule: Module

val coreDataModule = module {
    includes(platformCoreDataModule)

    single<ChirpLogger> { KermitLogger }
    single {
        HttpClientFactory(get()).create(get())
    }
    singleOf(::KtorAuthService) bind AuthService::class
}
