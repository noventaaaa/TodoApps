package com.example.todoapps.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapps.R
import com.example.todoapps.databinding.FragmentCreateTodoBinding
import com.example.todoapps.model.Todo
import com.example.todoapps.viewmodel.DetailTodoViewModel


class CreateTodoFragment : Fragment() {

    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var binding:FragmentCreateTodoBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTodoBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        btnAdd.setOnClickListener {
            Var radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)

            var todo = Todo(txtTitle.text.toString(),
                txtNotes.text.toString(), radio.tag.toString().toInt())

            val list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }

    }





}