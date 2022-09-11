package com.androidtask.workmanagersampleapp.utils

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.work.*
import com.androidtask.workmanagersampleapp.R
import com.androidtask.workmanagersampleapp.workmanager.PeriodicWorkManager
import java.util.*
import java.util.concurrent.TimeUnit

lateinit var periodicWorkManagerId:UUID

fun runPeriodicWorkManager(context: Context,observer: Observer<WorkInfo>,lifecycleOwner: LifecycleOwner)
{
    val constraintBuilder = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresBatteryNotLow(true).build();

    val periodicWorkRequest = PeriodicWorkRequestBuilder<PeriodicWorkManager>(1,TimeUnit.MINUTES)
        .setConstraints(constraintBuilder).build()
    WorkManager.getInstance(context).apply {
        enqueue(periodicWorkRequest)
        periodicWorkManagerId = periodicWorkRequest.id
        getWorkInfoByIdLiveData(periodicWorkManagerId).observe(lifecycleOwner,observer)
    }
}

fun cancelPeriodicWorkManagerById(context: Context)
{
    WorkManager.getInstance(context).cancelWorkById(periodicWorkManagerId)
}

fun getPeriodicWorkManagerResponse(context: Context,lifecycleOwner: LifecycleOwner)
{
    runPeriodicWorkManager(context, Observer { runPeriodicWorkManager->
        if (runPeriodicWorkManager.state == WorkInfo.State.SUCCEEDED) {
            Toast.makeText(
                context,
                runPeriodicWorkManager.outputData.getString("api_results"), Toast.LENGTH_SHORT).show()
        }
        else if(runPeriodicWorkManager.state == WorkInfo.State.BLOCKED)
        {
            Toast.makeText(
                context,
                context.getString(R.string.txt_state_blocked), Toast.LENGTH_SHORT).show()
        }
        else if(runPeriodicWorkManager.state == WorkInfo.State.CANCELLED)
        {
            Toast.makeText(
                context,
                context.getString(R.string.txt_state_cancelled), Toast.LENGTH_SHORT).show()
        }
        else if(runPeriodicWorkManager.state == WorkInfo.State.ENQUEUED)
        {
            Toast.makeText(
                context,
                context.getString(R.string.txt_state_enqueued), Toast.LENGTH_SHORT).show()
        }
        else if(runPeriodicWorkManager.state == WorkInfo.State.RUNNING)
        {
            Toast.makeText(
                context,
                context.getString(R.string.txt_state_running), Toast.LENGTH_SHORT).show()
        }
        else if(runPeriodicWorkManager.state == WorkInfo.State.FAILED)
        {
            Toast.makeText(
                context,
                context.getString(R.string.txt_state_failed), Toast.LENGTH_SHORT).show()
        }
    },lifecycleOwner)
}