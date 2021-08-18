package com.example.customers.ui.payments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customers.R
import com.example.customers.databinding.ItemPaymentBinding
import com.example.s2next.db.entities.Payments

class PaymentsAdapter(private var paymentsList: List<Payments>)
    : RecyclerView.Adapter<PaymentsAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemPaymentBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_payment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val payment = paymentsList[position]
        holder.binding.tvIDPayment.text = context.getString(R.string.client_id, payment.id.toString())
        holder.binding.tvIDCustomer.text = context.getString(R.string.payment_client_id, payment.Id_costumer.toString())
        holder.binding.tvTotal.text = context.getString(R.string.pays_total, payment.Amount.toString().toFloat())
    }

    fun setData(payments: List<Payments>){
        paymentsList = payments!!
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = paymentsList.size

}