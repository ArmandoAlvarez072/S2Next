package com.example.customers.ui.customer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customers.R
import com.example.customers.databinding.ItemClientBinding
import com.example.s2next.db.entities.Customer

class CustomerAdapter(private var customerList: List<Customer>,
                      private val listener : OnClientListener
) : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemClientBinding.bind(view)

        fun setListener(customer: Customer){
            binding.root.setOnClickListener{
                listener.onClick(customer)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_client, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val customer = customerList[position]
        holder.setListener(customer)
        holder.binding.tvID.text = context.getString(R.string.client_id, customer.id.toString())
        holder.binding.tvName.text = context.getString(R.string.client_name,
            customer.Name,
            customer.Middle_name,
            customer.Last_name,
            customer.Second_last_name)
        holder.binding.tvBirthDate.text = context.getString(R.string.client_birth_date, customer.Birthdate)
        holder.binding.tvGender.text = context.getString(R.string.client_gender, customer.gender)
    }

    fun setData(customers: List<Customer>){
        customerList = customers!!
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = customerList.size
}