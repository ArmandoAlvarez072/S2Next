package com.example.customers.ui.addcustomer

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.customers.R
import com.example.customers.databinding.ActivityUpdateCustomerBinding
import com.example.customers.db.CustomerApplication
import com.example.customers.ui.CustomerViewModelFactory
import com.example.customers.ui.CustomersViewModel
import com.example.s2next.db.entities.Customer
import java.util.ArrayList

class UpdateCustomerActivity : AppCompatActivity(){
    private lateinit var binding : ActivityUpdateCustomerBinding
    private val customersViewModel : CustomersViewModel by viewModels{
        CustomerViewModelFactory((application as CustomerApplication).repository)
    }
    private val arrayValues : Array<String> by lazy {
        this.resources.getStringArray(R.array.status_value)
    }

    private var id : Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpActionBar()
        initCustomer()
        configACTV()
        configButton()
    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initCustomer() {
        id = intent.getIntExtra("id", 1)
        val name = intent.getStringExtra("name")
        val middleName = intent.getStringExtra("middle")
        val lastName = intent.getStringExtra("last")
        val secondLastName = intent.getStringExtra("secondLast")
        val birthdate = intent.getStringExtra("birthdate")
        val gender = intent.getStringExtra("gender")

        binding.etFirstName.setText(name)
        binding.etMiddleName.setText(middleName)
        binding.etLastName.setText(lastName)
        binding.etSecondLastName.setText(secondLastName)
        binding.etBirthDate.setText(birthdate)
        binding.actvGender.setText(gender)
    }

    private fun configACTV() {
        val statusAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arrayValues)
        binding.actvGender.setAdapter(statusAdapter)
    }

    private fun configButton() {
        binding.btnRegisterCustomer.setOnClickListener {
            if(binding.etFirstName.text.toString().isEmpty()){
                binding.etFirstName.setError("Ingrese los datos")
            }else if(binding.etLastName.text.toString().isEmpty()){
                binding.etLastName.setError("Ingrese los datos")
            }else if(binding.etSecondLastName.text.toString().isEmpty()){
                binding.etSecondLastName.setError("Ingrese los datos")
            }else if(binding.etBirthDate.text.toString().isEmpty()){
                binding.etBirthDate.setError("Ingrese los datos")
            }else if(binding.actvGender.text.toString().isEmpty()){
                binding.actvGender.setError("Ingrese los datos")
            }else {
                val client = Customer(
                    id!!,
                    binding.etFirstName.text.toString().trim(),
                    binding.etMiddleName.text.toString(),
                    binding.etLastName.text.toString().trim(),
                    binding.etSecondLastName.text.toString().trim(),
                    binding.etBirthDate.text.toString().trim(),
                    binding.actvGender.text.toString().trim()
                )
                enableUI(false)
                customersViewModel.updateCustomer(client)
                finish()
            }
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