package com.example.summer_school_hw

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.summer_school_hw.model.workManager.ExampleWorker
import dagger.hilt.android.HiltAndroidApp
import okhttp3.internal.Internal.instance
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import androidx.work.PeriodicWorkRequestBuilder

@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    override fun onCreate() {
        super.onCreate()
        initWorker()
    }
    override fun getWorkManagerConfiguration() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()

    private fun initWorker() {
        val request: WorkRequest = PeriodicWorkRequestBuilder<ExampleWorker>(
            24, TimeUnit.HOURS).build()
        WorkManager.getInstance(applicationContext).enqueue(request)
    }
}
