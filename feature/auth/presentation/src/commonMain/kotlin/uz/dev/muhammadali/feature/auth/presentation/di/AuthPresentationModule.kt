package uz.dev.muhammadali.feature.auth.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import uz.dev.muhammadali.feature.auth.presentation.register.RegisterViewModel

val authPresentationModule = module {
    viewModelOf(::RegisterViewModel)
}