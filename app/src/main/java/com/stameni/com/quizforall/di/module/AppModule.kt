package com.stameni.com.quizforall.di.module

import com.stameni.com.quizforall.common.base.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: BaseApplication) {
    @Provides
    @Singleton
    fun provideApplication() = app
}