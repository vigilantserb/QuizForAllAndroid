package com.stameni.com.quizforall.data

import com.stameni.com.quizforall.common.ConnectivityInterceptor
import com.stameni.com.quizforall.data.models.BasicResponseModel
import com.stameni.com.quizforall.data.models.LoginResponseModel
import com.stameni.com.quizforall.data.models.quiz.QuizResponse
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*

private const val LOCAL_URL = "http://192.168.1.6:3000/"
private const val LOCAL_S8_URL = "http://192.168.42.84:3000/"
private const val HEROKU_URL = "https://safe-sea-80392.herokuapp.com/"

interface QuizApi {

    @POST("/mobile/login")
    fun loginPlayer(
        @Body loginInfo: HashMap<String, Any>
    ): Observable<Response<LoginResponseModel>>

    @POST("/mobile/player/recovery")
    fun recoveryEmail(
        @Body emailInfo: HashMap<String, Any>
    ): Observable<Response<BasicResponseModel>>

    @POST("/mobile/player/update")
    fun updatePassword(
        @Body updatePasswordInfo: HashMap<String, Any>
    ): Observable<Response<BasicResponseModel>>

    @GET("/mobile/quiz/latest")
    fun getLatestQuizzes(
        @Query("page") page: Int = 1,
        @Query("limit") pageSize: Int = 10
    ): Call<QuizResponse>

    @GET("/mobile/quiz/explore")
    fun getExploreQuizzes(
        @Query("page") page: Int = 1,
        @Query("limit") pageSize: Int = 10
    ): Call<QuizResponse>

    @GET("/mobile/player/favoritequizzes")
    fun getFavoriteQuizzes(
        @Query("page") page: Int = 1,
        @Query("limit") pageSize: Int = 10,
        @Query("playerId") id: String
    ): Call<QuizResponse>

    @POST("/mobile/register")
    fun signUp(
        @Body signUpinfo: HashMap<String, Any>
    ): Observable<Response<BasicResponseModel>>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): QuizApi {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("", "")
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(LOCAL_S8_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QuizApi::class.java)
        }
    }
}