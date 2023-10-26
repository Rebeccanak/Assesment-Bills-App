package com.example.assessment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.assessment.model.UpcomingBill

@Dao
interface UpcomingBillsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingBill(upcomingBill: UpcomingBill)

    @Query("SELECT * FROM UpcomingBills WHERE billId=:billId AND dueDate BETWEEN :startDate AND :endDate")
    fun queryExistingBill(billId:String,startDate:String,endDate:String):List<UpcomingBill>

    @Query("SELECT * FROM UpcomingBills WHERE frequency = :freq AND paid = :paid ORDER BY dueDate")
    fun getUpcomingBillsByFrequency(freq:String, paid: Boolean): LiveData<List<UpcomingBill>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUpcomingBill(upcomingBill: UpcomingBill)


    @Query("SELECT * FROM UpcomingBills WHERE paid = :paid ORDER BY dueDate DESC")
    fun getPaidBills(paid: Boolean=true):LiveData<List<UpcomingBill>>

    @Query("SELECT * FROM UpcomingBills WHERE synched = 0")
    fun getUnsynchedUpcomingBills():List<UpcomingBill>


    @Query("SELECT SUM(amount) FROM upcomingBills WHERE dueDate BETWEEN:startDate AND :endDate")
    fun getTotalMonthlyBills(startDate: String, endDate: String):Double

    @Query("SELECT SUM(amount) FROM upcomingBills WHERE paid=1 AND  dueDate BETWEEN:startDate AND :endDate")
    fun getPaidMonthlyBillsSum(startDate: String, endDate: String):Double

    @Query("SELECT SUM(amount) FROM upcomingBills WHERE paid=0 AND dueDate BETWEEN:startDate AND :endDate AND dueDate > :today")
    fun getUpcomingBillsThisMonth(startDate: String,endDate: String,today:String):Double

    @Query("SELECT SUM(amount) FROM upcomingBills WHERE paid=0 AND dueDate BETWEEN:startDate AND :endDate AND dueDate < :today")
    fun getOverdueBillsThisMonth(startDate: String,endDate: String,today:String):Double
}