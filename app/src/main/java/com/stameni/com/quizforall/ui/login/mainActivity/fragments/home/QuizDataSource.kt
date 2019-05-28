package com.stameni.com.quizforall.ui.login.mainActivity.fragments.home

import androidx.paging.PageKeyedDataSource
import com.stameni.com.quizforall.data.QuizApi
import com.stameni.com.quizforall.data.models.quiz.QuizResponse
import com.stameni.com.quizforall.data.models.quiz.SingleQuiz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuizDataSource(
    private val quizApi: QuizApi
) : PageKeyedDataSource<Int, SingleQuiz>() {

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>,callback: PageKeyedDataSource.LoadInitialCallback<Int, SingleQuiz>) {

        quizApi
            .getLatestQuizzes(
                FIRST_PAGE,
                PAGE_SIZE
            )
            .enqueue(object : Callback<QuizResponse> {
                override fun onFailure(call: Call<QuizResponse>, t: Throwable) {

                }
                override fun onResponse(call: Call<QuizResponse>, response: Response<QuizResponse>) {
                    if (response.body() != null) {
                        callback.onResult(response.body()!!.data, null, FIRST_PAGE + 1)
                    }
                }
            })

    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, SingleQuiz>) {
        quizApi
            .getLatestQuizzes(params.key,
                PAGE_SIZE
            )
            .enqueue(object : Callback<QuizResponse> {
                override fun onFailure(call: Call<QuizResponse>, t: Throwable) {

                }
                override fun onResponse(call: Call<QuizResponse>, response: Response<QuizResponse>) {
                    if (response.body() != null) {
                        val key = if (params.key > PAGE_SIZE) params.key - PAGE_SIZE else null
                        callback.onResult(response.body()!!.data, key)
                    }
                }
            })
    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, SingleQuiz>) {
        quizApi
            .getLatestQuizzes(params.key,
                PAGE_SIZE
            )
            .enqueue(object : Callback<QuizResponse> {
                override fun onFailure(call: Call<QuizResponse>, t: Throwable) {

                }
                override fun onResponse(call: Call<QuizResponse>, response: Response<QuizResponse>) {
                    if (response.body() != null) {
                        val key = if (!response.body()!!.data.isEmpty()) params.key + 1 else null
                        callback.onResult(response.body()!!.data, key)
                    }
                }
            })
    }

    companion object {
        val PAGE_SIZE = 10
        private val FIRST_PAGE = 1
    }
}