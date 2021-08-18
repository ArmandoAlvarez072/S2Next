package com.example.customers.db

import android.app.Application
import com.example.customers.repository.CustomersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CustomerApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())


    val database by lazy { CustomerRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { CustomersRepository(database.customerDao()) }
}