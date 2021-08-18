package com.example.s2next.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "customer")
class Customer(
    @PrimaryKey(autoGenerate = true) val id : Int,

    @ColumnInfo(name = "name")
    val Name : String,
    val Middle_name : String,
    val Last_name : String,
    val Second_last_name : String,
    val Birthdate : String,
    val gender : String,
)
