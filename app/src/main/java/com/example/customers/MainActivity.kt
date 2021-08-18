package com.example.customers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customers.databinding.ActivityMainBinding
import com.example.customers.ui.addcustomer.AddCustomerActivity
import com.example.customers.ui.addpayment.AddPaymentActivity
import com.example.customers.ui.customer.CustomersActivity
import com.example.customers.ui.payments.PaymentsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configButtons()
    }

    private fun configButtons() {
        binding.btnNewCustomer.setOnClickListener {
            startActivity(Intent(this, AddCustomerActivity::class.java))
        }
        binding.btnNewPayment.setOnClickListener {
            startActivity(Intent(this, AddPaymentActivity::class.java))
        }
        binding.btnViewCustomer.setOnClickListener {
            startActivity(Intent(this, CustomersActivity::class.java))
        }
        binding.btnViewPayments.setOnClickListener {
            startActivity(Intent(this, PaymentsActivity::class.java))
        }
    }
}