package com.example.customers.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.customers.db.dao.CustomerDao
import com.example.s2next.db.entities.Customer
import com.example.s2next.db.entities.Payments
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Customer::class, Payments::class), version = 1, exportSchema = false)
abstract class CustomerRoomDatabase : RoomDatabase() {

    abstract fun customerDao() : CustomerDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var wordDao = database.customerDao()

                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: CustomerRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CustomerRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CustomerRoomDatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}