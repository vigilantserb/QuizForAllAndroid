package com.stameni.com.quizforall.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Page(
    @PrimaryKey
    val page: Int
)