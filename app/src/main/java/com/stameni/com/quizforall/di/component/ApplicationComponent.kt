package com.stameni.com.quizforall.di.component

import com.stameni.com.quizforall.common.base.BaseApplication
import com.stameni.com.quizforall.di.module.AppModule
import com.stameni.com.quizforall.di.module.NetworkModule
import com.stameni.com.quizforall.di.module.PresentationModule
import com.stameni.com.quizforall.di.module.ViewModelModule
import dagger.Component

@Component(modules = [AppModule::class])
interface ApplicationComponent {
    fun inject(app: BaseApplication)
    fun newPresentationComponent(
        networkModule: NetworkModule,
        viewModelModule: ViewModelModule,
        presentationModule: PresentationModule
    ): PresentationComponent
}