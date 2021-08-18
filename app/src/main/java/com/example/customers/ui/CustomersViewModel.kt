package com.example.customers.ui


import androidx.lifecycle.*
import com.example.customers.repository.CustomersRepository
import com.example.s2next.db.entities.Customer
import com.example.s2next.db.entities.Payments
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class CustomersViewModel(private val repository: CustomersRepository) : ViewModel() {
    val allCustomer: LiveData<List<Customer>> = repository.allCustomer.asLiveData()

    val allPayments : LiveData<List<Payments>> = repository.allPayments.asLiveData()

    fun getPayments(date: String) : LiveData<List<Payments>>{
        return repository.getPayments(date).asLiveData()
    }

    fun newPayment(payments: Payments) = viewModelScope.launch {
        repository.newPayment(payments)
    }

    fun newCustomer(customer: Customer) = viewModelScope.launch {
        repository.newCustomer(customer)
    }

    fun updateCustomer(customer: Customer) = viewModelScope.launch {
        repository.updateCustomer(customer)
    }
}


class CustomerViewModelFactory(private val repository: CustomersRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CustomersViewModel::class.java)){
            @Suppress("UNCHEKED_CAST")
            return CustomersViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}