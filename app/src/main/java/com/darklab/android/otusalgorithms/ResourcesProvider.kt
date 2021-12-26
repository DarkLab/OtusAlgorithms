package com.darklab.android.otusalgorithms

import android.app.Application
import androidx.annotation.StringRes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesProvider @Inject constructor(
    private val context: Application
) {

    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }
}