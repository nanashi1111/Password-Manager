package com.klmobile.passwordmanager

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.*
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class PasswordManagerApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    setupTimber()
  }

  private fun setupTimber() {
    if (BuildConfig.DEBUG) {
      plant(DebugTree())
    } else {
      plant(object : Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        }
      })
    }
  }
}