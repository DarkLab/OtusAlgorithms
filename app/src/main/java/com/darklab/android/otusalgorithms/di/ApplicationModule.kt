package com.darklab.android.otusalgorithms.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val context: Application) {

    @Provides
    fun context() = context

}