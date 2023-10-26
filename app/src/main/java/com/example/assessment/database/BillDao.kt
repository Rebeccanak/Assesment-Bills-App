package com.example.assessment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assessment.model.Bill

@Dao
interface BillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBill(bill: Bill)


//    @Query("SELECT * FROM Bills ORDER BY dueDate")
//    fun getAllBills(): LiveData<List<Bill>>

    @Query("SELECT * FROM BILLS WHERE frequency=:freq")
    fun getRecurringBills(freq:String):List<Bill>

    @Query("SELECT  * FROM Bills WHERE synched = 0")
    fun getUnsynchedBills():List<Bill>






//    @Query("SELECT * FROM Bills WHERE  billId=:billId")
//    fun getBillById(billId:Int):LiveData<Bill>
//
//
//    @Delete
//    suspend fun deleteBillById(bill: Bill)
}
