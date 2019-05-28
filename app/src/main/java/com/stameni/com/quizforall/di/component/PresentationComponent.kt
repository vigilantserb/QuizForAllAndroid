package com.stameni.com.quizforall.di.component

import com.stameni.com.quizforall.di.module.NetworkModule
import com.stameni.com.quizforall.di.module.PresentationModule
import com.stameni.com.quizforall.di.module.RoomModule
import com.stameni.com.quizforall.di.module.ViewModelModule
import com.stameni.com.quizforall.ui.forgotPassword.ForgotPasswordActivity
import com.stameni.com.quizforall.ui.forgotPassword.passwordReset.PasswordResetActivity
import com.stameni.com.quizforall.ui.login.LoginActivity
import com.stameni.com.quizforall.ui.login.mainActivity.fragments.explore.ExploreFragment
import com.stameni.com.quizforall.ui.login.mainActivity.fragments.favorites.FavoritesFragment
import com.stameni.com.quizforall.ui.login.mainActivity.fragments.home.HomeFragment
import com.stameni.com.quizforall.ui.signUp.SignupActivity
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(
    modules = [
        NetworkModule::class,
        ViewModelModule::class,
        PresentationModule::class,
        RoomModule::class
    ]
)

interface PresentationComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(forgotPasswordActivity: ForgotPasswordActivity)
    fun inject(passwordResetActivity: PasswordResetActivity)
    fun inject(signupActivity: SignupActivity)

    fun inject(homeFragment: HomeFragment)
    fun inject(exploreFragment: ExploreFragment)
    fun inject(favoritesFragment: FavoritesFragment)
}