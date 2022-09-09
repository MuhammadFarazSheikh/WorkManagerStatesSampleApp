package com.androidtask.workmanagersampleapp.utils

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.androidtask.workmanagersampleapp.workmanager.OneTimeWorkManager

fun runOneTimeWorker(context: Context,observer: Observer<WorkInfo>,lifecycleOwner: LifecycleOwner)
{
    val oneTimeWorkManager = OneTimeWorkRequestBuilder<OneTimeWorkManager>().build()
    WorkManager
        .getInstance(context)
        .apply {
            enqueue(oneTimeWorkManager)
            getWorkInfoByIdLiveData(oneTimeWorkManager.id).observe(lifecycleOwner,observer)
        }
}