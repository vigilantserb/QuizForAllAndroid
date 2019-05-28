package com.stameni.com.quizforall.common.base

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.stameni.com.quizforall.di.component.ApplicationComponent
import com.stameni.com.quizforall.di.component.DaggerApplicationComponent
import com.stameni.com.quizforall.di.module.AppModule

class BaseApplication: Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        AndroidThreeTen.init(this)
    }
}