package com.example.s2next.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "payments")
class Payments(
    @PrimaryKey(autoGenerate = true)
    val id : Int,

    val Id_costumer : Int,
    val Register_date : String,
    val Date : String,
    val Amount : Double

)
