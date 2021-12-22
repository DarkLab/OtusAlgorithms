package com.darklab.android.otusalgorithms

import android.app.Application
import com.darklab.android.otusalgorithms.di.ApplicationGraph
import com.darklab.android.otusalgorithms.di.ApplicationModule
import com.darklab.android.otusalgorithms.di.DaggerApplicationGraph

class OtusApplication : Application() {
    val appComponent: ApplicationGraph = DaggerApplicationGraph
        .builder()
        .applicationModule(ApplicationModule(this))
        .build()
}