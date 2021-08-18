package com.example.customers.ui.addcustomer

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.customers.R
import com.example.customers.databinding.ActivityAddCustomerBinding
import com.example.customers.db.CustomerApplication
import com.example.customers.ui.CustomerViewModelFactory
import com.example.customers.ui.CustomersViewModel
import com.example.customers.ui.customer.CustomerAdapter
import com.example.customers.ui.customer.CustomersActivity
import com.example.customers.ui.customer.OnClientListener
import com.example.s2next.db.entities.Customer
import java.util.ArrayList

class AddCustomerActivity : AppCompatActivity(){
    private lateinit var binding: ActivityAddCustomerBinding
    private val customersViewModel : CustomersViewModel by viewModels{
        CustomerViewModelFactory((application as CustomerApplication).repository)
    }
    private val arrayValues : Array<String> by lazy {
        this.resources.getStringArray(R.array.status_value)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpActionBar()
        configACTV()
        configButton()
    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun configACTV() {
        val statusAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arrayValues)
        binding.actvGender.setAdapter(statusAdapter)
    }

    private fun configButton() {
        binding.btnRegisterCustomer.setOnClickListener {
            val client = Customer(0,
                binding.etFirstName.text.toString(),
                binding.etMiddleName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etSecondLastName.text.toString(),
                binding.etBirthDate.text.toString(),
                binding.actvGender.text.toString())
            enableUI(false)
            customersViewModel.newCustomer(client)
            finish()
        }
    }

    private fun enableUI(enable : Boolean){
        binding?.let {
            with(it){
                btnRegisterCustomer.isEnabled = enable
                etFirstName.isEnabled = enable
                etMiddleName.isEnabled = enable
                etLastName.isEnabled = enable
                etSecondLastName.isEnabled = enable
                actvGender.isEnabled = enable
            }
        }
    }

}