package com.example.assessment.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment.model.Bill
import com.example.assessment.model.BillsSummary
import com.example.assessment.model.UpcomingBill
import com.example.assessment.repository.BillsRepository
import kotlinx.coroutines.launch

class BillsViewModel:ViewModel() {
    private val billsRepo= BillsRepository()
    val summaryLiveData= MutableLiveData<BillsSummary>()

    fun saveBill(bill: Bill){
        viewModelScope.launch {
            billsRepo.saveBill(bill)
        }
    }
    fun insertUpcomingBill(upcomingBill: UpcomingBill){
        viewModelScope.launch {
            billsRepo.insertUpcomingBill(upcomingBill)
        }
    }
//    fun getAllBills(): LiveData<List<Bill>> {
//        return billsRepo.getAllBills()
//    }

    fun getUpcomingBillsByFrequency(freq:String):LiveData<List<UpcomingBill>>{
        return billsRepo.getUpcomingBillsByFrequency(freq)
    }

    fun updateUpcomingBill(upcomingBill: UpcomingBill){
        viewModelScope.launch {
            billsRepo.updateUpcomingBill(upcomingBill)
        }
    }

    fun getPaidBills():LiveData<List<UpcomingBill>>{
        return billsRepo.getPaidBills()
    }

    fun fetchRemoteBills(){
        viewModelScope.launch {
            billsRepo.fetchRemoteBills()
            billsRepo.fetchRemoteUpcomingBills()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonthlySummary(){
       viewModelScope.launch {
           summaryLiveData.postValue(billsRepo.getMonthlySummary().value)
       }
    }


}