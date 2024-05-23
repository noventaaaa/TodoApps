package com.example.todoapps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoapps.model.Todo
import com.example.todoapps.model.TodoDatabase
import com.example.todoapps.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    val db = buildDb(getApplication())
    private val job = Job()
    fun addTodo(list:List<Todo>) {
        launch {
            val db = TodoDatabase.buildDatabase(
                getApplication()
            )
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}