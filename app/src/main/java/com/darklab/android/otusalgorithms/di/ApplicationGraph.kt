package com.darklab.android.otusalgorithms.di

import com.darklab.android.otusalgorithms.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationGraph {

    fun inject(activity: MainActivity)
}