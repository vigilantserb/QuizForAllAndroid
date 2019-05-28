package com.stameni.com.quizforall.di.module

import android.content.Context
import com.stameni.com.quizforall.common.ConnectivityInterceptor
import com.stameni.com.quizforall.common.ConnectivityInterceptorImpl
import com.stameni.com.quizforall.data.QuizApi
import com.stameni.com.quizforall.data.network.QuizNetworkDataSource
import com.stameni.com.quizforall.data.network.QuizNetworkDataSourceImpl
import com.stameni.com.quizforall.data.repository.QuizRepository
import com.stameni.com.quizforall.data.repository.QuizRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getConectivityInterceptor(context: Context): ConnectivityInterceptor = ConnectivityInterceptorImpl(context)

    @Singleton
    @Provides
    fun getQuizApi(connectivityInterceptor: ConnectivityInterceptor): QuizApi =
        QuizApi(connectivityInterceptor)

    @Provides
    @Singleton
    fun getQuizNetworkDataSource(quizApi: QuizApi): QuizNetworkDataSource =
        QuizNetworkDataSourceImpl(quizApi)

    @Provides
    @Singleton
    fun getQuizRepository(
        quizNetworkDataSource: QuizNetworkDataSource
    ): QuizRepository =
        QuizRepositoryImpl(quizNetworkDataSource)
}