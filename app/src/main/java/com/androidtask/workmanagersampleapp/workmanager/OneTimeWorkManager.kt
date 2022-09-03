package com.androidtask.workmanagersampleapp.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class OneTimeWorkManager(context: Context,workerParameters: WorkerParameters): Worker(context,workerParameters)
{
    override fun doWork(): Result {
        return Result.success()
    }
}