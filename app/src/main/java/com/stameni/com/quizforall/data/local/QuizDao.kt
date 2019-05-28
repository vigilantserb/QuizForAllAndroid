package com.stameni.com.quizforall.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stameni.com.quizforall.data.models.quiz.SingleQuiz
import io.reactivex.Observable

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : List<SingleQuiz>)

    @Query("SELECT * FROM SingleQuiz")
    fun posts() : DataSource.Factory<Int, SingleQuiz>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun latestPage(page: Page)

    @Query("SELECT * FROM Page")
    fun getLastPage() : LiveData<Page>
}
