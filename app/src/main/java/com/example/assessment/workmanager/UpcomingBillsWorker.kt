package com.example.assessment.workmanager

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.assessment.repository.BillsRepository

class UpcomingBillsWorker(context: Context,workerParams: WorkerParameters):
    CoroutineWorker(context, workerParams) {
    val billsRepo = BillsRepository()
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        billsRepo.createRecurringWeeklyBills()
        billsRepo.createRecurringAnnuallyBills()
        billsRepo.createRecurringQuarterlyBills()
        billsRepo.createRecurringMonthlyBills()

        return Result.success()
    }
}