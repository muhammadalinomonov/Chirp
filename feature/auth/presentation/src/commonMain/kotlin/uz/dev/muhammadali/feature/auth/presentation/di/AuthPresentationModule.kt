package uz.dev.muhammadali.feature.auth.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import uz.dev.muhammadali.feature.auth.presentation.email_verification.EmailVerificationViewModel
import uz.dev.muhammadali.feature.auth.presentation.login.LoginViewModel
import uz.dev.muhammadali.feature.auth.presentation.register.RegisterViewModel
import uz.dev.muhammadali.feature.auth.presentation.register_success.RegisterSuccessViewModel

val authPresentationModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::RegisterSuccessViewModel)
    viewModelOf(::EmailVerificationViewModel)
    viewModelOf(::LoginViewModel)
}