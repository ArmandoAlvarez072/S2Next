package com.example.customers.ui.payments

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.customers.databinding.ActivityPaymentsBinding
import com.example.customers.db.CustomerApplication
import com.example.customers.ui.CustomerViewModelFactory
import com.example.customers.ui.CustomersViewModel
import com.example.s2next.db.entities.Payments
import java.util.ArrayList

class PaymentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentsBinding
    private lateinit var adapter: PaymentsAdapter

    private val customersViewModel : CustomersViewModel by viewModels{
        CustomerViewModelFactory((application as CustomerApplication).repository)
    }

    private var paymentsList : List<Payments> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpActionBar()
        configRecyclerView()
        getAllPayments()
        configButton()
    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getAllPayments() {
        customersViewModel.allPayments.observe(this, Observer {
            paymentsList = it
            adapter.setData(paymentsList)
        })
    }

    private fun configButton() {
        binding.btnSearch.setOnClickListener {
            val paymentDate = binding.etSearch.text.toString()
            vmObserverDate(paymentDate)
        }
    }

    private fun vmObserverDate(date : String){
        customersViewModel.getPayments(date).observe(this, Observer {
            paymentsList = it
            adapter.setData(paymentsList)
        })
    }

    private fun configRecyclerView() {
        adapter = PaymentsAdapter(arrayListOf())
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(
                context, 1,
                GridLayoutManager.VERTICAL, false
            )
            adapter = this@PaymentsActivity.adapter
        }
    }
}