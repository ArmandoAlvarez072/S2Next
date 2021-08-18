package com.example.customers.ui.customer

import com.example.s2next.db.entities.Customer

interface OnClientListener {
    fun onClick(customer: Customer)
    fun getCostumerSelected() : Customer?
}