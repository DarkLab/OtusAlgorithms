package com.darklab.android.otusalgorithms.di

import com.darklab.android.otusalgorithms.MainActivity
import dagger.Subcomponent

@Subcomponent
interface MainViewComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainViewComponent
    }

    fun inject(activity: MainActivity)
}