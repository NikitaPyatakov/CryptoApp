package com.example.cryptoapp.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.cryptoapp.di.ChildWorkerFactory
import javax.inject.Inject
import javax.inject.Provider

class RefreshDataWorkerFactory @Inject constructor(
    private val workerProvides: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            RefreshDataWorker::class.simpleName -> {
                val childWorkerFactory = workerProvides[RefreshDataWorker::class.java]?.get()
                return childWorkerFactory?.create(appContext, workerParameters)
            }
            else -> {
                null
            }
        }
    }
}