package com.stameni.com.quizforall.common.base

import androidx.fragment.app.Fragment
import com.stameni.com.quizforall.di.component.ApplicationComponent
import com.stameni.com.quizforall.di.module.PresentationModule
import com.stameni.com.quizforall.di.component.PresentationComponent
import androidx.annotation.UiThread
import com.stameni.com.quizforall.di.module.NetworkModule
import com.stameni.com.quizforall.di.module.ViewModelModule


abstract class BaseFragment : Fragment() {

    private var mIsInjectorUsed: Boolean = false

    protected val presentationComponent: PresentationComponent

        @UiThread
        get() {
            if (mIsInjectorUsed) {
                throw RuntimeException("there is no need to use injector more than once")
            }
            mIsInjectorUsed = true
            return applicationComponent
                .newPresentationComponent(NetworkModule(), ViewModelModule(), PresentationModule(activity!!))

        }

    private val applicationComponent: ApplicationComponent
        get() = (activity!!.application as BaseApplication).component
}
