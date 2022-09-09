package com.androidtask.workmanagersampleapp.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.montymobile.callsignature.networking.ApiEndPoints
import com.montymobile.callsignature.networking.buildApiServiceForWeatherUpdates

class OneTimeWorkManager(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        val result =
            buildApiServiceForWeatherUpdates().getData("Pakistan", ApiEndPoints.WEATHER_API_APP_ID)
                .execute()
        return if (result.isSuccessful) {
            Result.success(workDataOf("api_results" to "success"))
        }
        else
        {
            Result.failure(workDataOf("api_results" to "failure"))
        }
    }
}