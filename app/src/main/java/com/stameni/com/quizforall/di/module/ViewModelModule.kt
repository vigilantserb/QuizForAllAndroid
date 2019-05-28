package com.stameni.com.quizforall.di.module

import android.content.SharedPreferences
import com.stameni.com.quizforall.ui.signUp.SignUpViewModelFactory
import com.stameni.com.quizforall.data.QuizApi
import com.stameni.com.quizforall.data.repository.QuizRepository
import com.stameni.com.quizforall.ui.forgotPassword.ForgotPasswordViewModelFactory
import com.stameni.com.quizforall.ui.forgotPassword.passwordReset.PasswordResetActivityViewModelFactory
import com.stameni.com.quizforall.ui.login.LoginActivityViewModelFactory
import com.stameni.com.quizforall.ui.login.mainActivity.fragments.explore.ExploreViewModelFactory
import com.stameni.com.quizforall.ui.login.mainActivity.fragments.favorites.FavoritesViewModelFactory
import com.stameni.com.quizforall.ui.login.mainActivity.fragments.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun getLoginActivityViewModelFactory(
        quizRepository: QuizRepository
    ): LoginActivityViewModelFactory =
        LoginActivityViewModelFactory(quizRepository)

    @Provides
    fun getForgotPasswordActivityViewModelFactory(
        quizRepository: QuizRepository
    ): ForgotPasswordViewModelFactory =
        ForgotPasswordViewModelFactory(quizRepository)

    @Provides
    fun getPasswordResetActivityViewModelFactory(
        quizRepository: QuizRepository
    ): PasswordResetActivityViewModelFactory =
        PasswordResetActivityViewModelFactory(quizRepository)

    @Provides
    fun getHomeFragmentViewModelFactory(
        quizApi: QuizApi
    ): HomeViewModelFactory =
        HomeViewModelFactory(quizApi)

    @Provides
    fun getSignUpViewModelFactory(
        quizRepository: QuizRepository
    ): SignUpViewModelFactory =
        SignUpViewModelFactory(quizRepository)

    @Provides
    fun getExploreQuizzesViewModelFactory(
        quizApi: QuizApi
    ): ExploreViewModelFactory =
        ExploreViewModelFactory(quizApi)

    @Provides
    fun getFavoriteQuizzesViewModelFactory(
        quizApi: QuizApi,
        sharedPreferences: SharedPreferences
    ): FavoritesViewModelFactory =
        FavoritesViewModelFactory(quizApi, sharedPreferences)
}