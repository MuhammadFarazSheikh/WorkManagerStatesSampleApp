package com.androidtask.workmanagersampleapp.utils

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.work.*
import com.androidtask.workmanagersampleapp.R
import com.androidtask.workmanagersampleapp.workmanager.OneTimeWorkManager

fun runOneTimeWorker(
    context: Context,
    observer: Observer<WorkInfo>,
    lifecycleOwner: LifecycleOwner
) {
    val constraintsBuilder = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresBatteryNotLow(true).build()
    val oneTimeWorkManager = OneTimeWorkRequestBuilder<OneTimeWorkManager>().setConstraints(constraintsBuilder).build()
    WorkManager
        .getInstance(context)
        .apply {
            enqueue(oneTimeWorkManager)
            getWorkInfoByIdLiveData(oneTimeWorkManager.id).observe(lifecycleOwner, observer)
        }
}

fun getOneTimeWorkManagerResponse(context: Context,lifecycleOwner: LifecycleOwner)
{
    runOneTimeWorker(context, Observer { oneTimeWorkManagerListener->
        if (oneTimeWorkManagerListener.state == WorkInfo.State.SUCCEEDED) {
            Toast.makeText(
                context,
                oneTimeWorkManagerListener.outputData.getString("api_results"), Toast.LENGTH_SHORT).show()
        }
        else if(oneTimeWorkManagerListener.state == WorkInfo.State.BLOCKED)
        {
            Toast.makeText(
                context,
                context.getString(R.string.txt_state_blocked), Toast.LENGTH_SHORT).show()
        }
        else if(oneTimeWorkManagerListener.state == WorkInfo.State.CANCELLED)
        {
            Toast.makeText(
                context,
                context.getString(R.string.txt_state_cancelled), Toast.LENGTH_SHORT).show()
        }
        else if(oneTimeWorkManagerListener.state == WorkInfo.State.ENQUEUED)
        {
            Toast.makeText(
                context,
                context.getString(R.string.txt_state_enqueued), Toast.LENGTH_SHORT).show()
        }
        else if(oneTimeWorkManagerListener.state == WorkInfo.State.RUNNING)
        {
            Toast.makeText(
                context,
                context.getString(R.string.txt_state_running), Toast.LENGTH_SHORT).show()
        }
        else if(oneTimeWorkManagerListener.state == WorkInfo.State.FAILED)
        {
            Toast.makeText(
                context,
                context.getString(R.string.txt_state_failed), Toast.LENGTH_SHORT).show()
        }
    },lifecycleOwner)
}