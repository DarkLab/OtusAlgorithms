package com.darklab.android.otusalgorithms

import android.app.Application
import android.content.res.AssetManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AssetsProvider @Inject constructor(
    context: Application
) {
    private val assets: AssetManager = context.assets

    fun getFileContent(filePath: String): Array<String> {
        return assets.open(filePath).bufferedReader().use {
            it.readLines().toTypedArray()
        }
    }

    fun getAllAssetsIn(filePath: String): Array<String>? {
        return assets.list(filePath)
    }

    fun readResultFrom(filePath: String): String {
        return assets.open(filePath).bufferedReader().use {
            it.readText().trim()
        }
    }
}