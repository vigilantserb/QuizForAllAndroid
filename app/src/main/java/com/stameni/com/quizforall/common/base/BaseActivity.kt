package com.stameni.com.quizforall.common.base

import androidx.appcompat.app.AppCompatActivity
import com.stameni.com.quizforall.di.component.ApplicationComponent
import com.stameni.com.quizforall.di.component.PresentationComponent
import com.stameni.com.quizforall.di.module.NetworkModule
import com.stameni.com.quizforall.di.module.PresentationModule
import com.stameni.com.quizforall.di.module.ViewModelModule

abstract class BaseActivity : AppCompatActivity() {

    protected fun getPresentationComponent(): PresentationComponent {
        return getApplicationComponent()
            .newPresentationComponent(NetworkModule(), ViewModelModule(), PresentationModule(this))
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (this.application as BaseApplication).component
    }

}