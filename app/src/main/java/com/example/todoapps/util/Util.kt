package com.example.todoapps.util

import android.content.Context
import com.example.todoapps.model.TodoDatabase

val DB_NAME = "newtododb"
fun buildDb(context: Context): TodoDatabase {
    val db = TodoDatabase.buildDatabase(context)
    return db
}