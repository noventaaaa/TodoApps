package com.example.todoapps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todoapps.model.Todo
import com.example.todoapps.model.TodoDatabase
import com.example.todoapps.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    val db = buildDb(getApplication())
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            val db = TodoDatabase.buildDatabase(
                getApplication()
            )

            todoLD.postValue(db.todoDao().selectAllTodo())
            loadingLD.postValue(false)
        }
    }

    fun clearTask(todo: Todo) {
        launch {
            val db = TodoDatabase.buildDatabase(
                getApplication()
            )
            db.todoDao().deleteTodo(todo)
            todoLD.postValue(db.todoDao().selectAllTodo())
        }
    }
}
