package com.example.customers.ui.addpayment

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.customers.R
import com.example.customers.databinding.ActivityAddPaymentBinding
import com.example.customers.db.CustomerApplication
import com.example.customers.ui.CustomerViewModelFactory
import com.example.customers.ui.CustomersViewModel
import com.example.s2next.db.entities.Payments
import java.text.SimpleDateFormat
import java.util.*

class AddPaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPaymentBinding
    private val customersViewModel : CustomersViewModel by viewModels{
        CustomerViewModelFactory((application as CustomerApplication).repository)
    }
    private val arrayValues : Array<String> by lazy {
        this.resources.getStringArray(R.array.status_value)
    }

    private val sdf = SimpleDateFormat("yyyy/m/dd")
    private val currentDate = sdf.format(Date())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpActionBar()
        configButton()
    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun configButton() {
        binding.btnRegisterPayment.setOnClickListener {
            enableUI(false)
            val pay = Payments(0,
                binding.etIDCustomer.text.toString().toInt(),
                binding.etPaymentDate.text.toString(),
                currentDate,
                binding.etTotal.text.toString().toDouble())
            customersViewModel.newPayment(pay)
            finish()
        }
    }

    private fun enableUI(enable : Boolean){
        binding?.let {
            with(it){
                btnRegisterPayment.isEnabled = enable
                etIDCustomer.isEnabled = enable
                etPaymentDate.isEnabled = enable
                etTotal.isEnabled = enable
            }
        }
    }
}