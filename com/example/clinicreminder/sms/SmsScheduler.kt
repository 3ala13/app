package com.example.clinicreminder.sms

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class SmsWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val phoneNumber = inputData.getString("phoneNumber") ?: return Result.failure()
        val message = inputData.getString("message") ?: return Result.failure()
        SmsSender.sendSMS(applicationContext as Activity, phoneNumber, message)
        return Result.success()
    }
}

object SmsScheduler {
    fun scheduleSMS(context: Context, phoneNumber: String, message: String, delayHours: Long) {
        val data = workDataOf(
            "phoneNumber" to phoneNumber,
            "message" to message
        )
        val request = OneTimeWorkRequestBuilder<SmsWorker>()
            .setInitialDelay(delayHours, TimeUnit.HOURS)
            .setInputData(data)
            .build()

        WorkManager.getInstance(context).enqueue(request)
    }
}
