package uz.dev.muhammadali.chrip.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import uz.dev.muhammadali.core.data.di.coreDataModule
import uz.dev.muhammadali.feature.auth.presentation.di.authPresentationModule

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            coreDataModule,
            authPresentationModule
        )
    }
}