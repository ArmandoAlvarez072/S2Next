package com.example.customers.ui.customer

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.customers.databinding.ActivityCustomersBinding
import com.example.customers.db.CustomerApplication
import com.example.customers.ui.CustomerViewModelFactory
import com.example.customers.ui.CustomersViewModel
import com.example.customers.ui.addcustomer.AddCustomerActivity
import com.example.customers.ui.addcustomer.UpdateCustomerActivity
import com.example.customers.ui.payments.PaymentsActivity
import com.example.s2next.db.entities.Customer
import java.util.ArrayList

class CustomersActivity : AppCompatActivity() ,OnClientListener {
    private lateinit var binding: ActivityCustomersBinding
    private lateinit var adapter: CustomerAdapter

    private val customersViewModel : CustomersViewModel by viewModels{
        CustomerViewModelFactory((application as CustomerApplication).repository)
    }

    private var customerList : List<Customer> = ArrayList()
    private var customerSelected : Customer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpActionBar()
        configRecyclerView()
        vmObserver()
    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun vmObserver(){
        customersViewModel.allCustomer.observe(this, Observer {
            customerList = it
            adapter.setData(customerList)
        })
    }

    private fun configRecyclerView() {
        adapter = CustomerAdapter(arrayListOf(), this)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(
                context, 1,
                GridLayoutManager.VERTICAL, false
            )
            adapter = this@CustomersActivity.adapter
        }
    }


    override fun onClick(customer: Customer) {
        customerSelected = customer
        val id :Int = customer.id
        val name = customer.Name
        val middleName = customer.Middle_name
        val lastName = customer.Last_name
        val secondLastName = customer.Second_last_name
        val birthdate = customer.Birthdate
        val gender = customer.gender

        val intent = Intent(this, UpdateCustomerActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("name", name)
        intent.putExtra("middle", middleName)
        intent.putExtra("last", lastName)
        intent.putExtra("secondLast", secondLastName)
        intent.putExtra("birthdate", birthdate)
        intent.putExtra("gender", gender)
        startActivity(intent)
    }

    override fun getCostumerSelected(): Customer? = customerSelected
}