package com.androidtask.workmanagersampleapp.utils

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.androidtask.workmanagersampleapp.workmanager.OneTimeWorkManager

fun runOneTimeWorker(context: Context)
{
    WorkManager
        .getInstance(context)
        .enqueue(OneTimeWorkRequestBuilder<OneTimeWorkManager>().build())
}