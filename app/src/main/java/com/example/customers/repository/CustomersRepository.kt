package com.example.customers.repository

import androidx.annotation.WorkerThread
import com.example.customers.db.dao.CustomerDao
import com.example.s2next.db.entities.Customer
import com.example.s2next.db.entities.Payments
import kotlinx.coroutines.flow.Flow

class CustomersRepository(private val customerDao: CustomerDao) {
    val allCustomer : Flow<List<Customer>> = customerDao.getCustomer()

    val allPayments : Flow<List<Payments>> = customerDao.getAllPayments()

    fun getPayments(date: String) : Flow<List<Payments>> {
         return customerDao.getPayments(date)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun newPayment(payments: Payments){
        customerDao.newPayment(payments)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun newCustomer(customer: Customer){
        customerDao.newCustomer(customer)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateCustomer(customer: Customer){
        customerDao.updateCustomer(customer)
    }
}