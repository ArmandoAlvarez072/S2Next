package com.example.customers.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.s2next.db.entities.Customer
import com.example.s2next.db.entities.Payments
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customer")
    fun getCustomer() : Flow<List<Customer>>

    @Query("SELECT * FROM payments")
    fun getAllPayments() : Flow<List<Payments>>

    @Query("SELECT * FROM payments WHERE Register_date = :date")
    fun getPayments(date: String) :Flow<List<Payments>>

    @Insert
    suspend fun newCustomer(customer: Customer)

    @Insert
    suspend fun newPayment(payments: Payments)

    @Update
    suspend fun updateCustomer(customer: Customer)
}