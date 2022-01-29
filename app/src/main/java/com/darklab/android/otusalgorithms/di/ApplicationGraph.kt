package com.darklab.android.otusalgorithms.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, MainViewModule::class])
interface ApplicationGraph {

    fun mainViewComponent(): MainViewComponent.Factory
}