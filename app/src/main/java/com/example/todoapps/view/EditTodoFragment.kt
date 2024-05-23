package com.example.todoapps.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapps.R
import com.example.todoapps.databinding.FragmentCreateTodoBinding
import com.example.todoapps.viewmodel.DetailTodoViewModel


class EditTodoFragment : Fragment() {


    private lateinit var binding: FragmentCreateTodoBinding
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTodoBinding.inflate(inflater,container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        txtJudulTodo.text = "Edit Todo"
        btnAdd.text = "Save Changes"

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            txtTitle.setText(it.title)
            txtNotes.setText(it.notes)
        })

        when (it.priority) {
            1 -> radioLow.isChecked = true
            2 -> radioMedium.isChecked = true
            else -> radioHigh.isChecked = true
        }


    btnAdd.setOnClickListener {
        val radio =
            view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
        viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
            radio.tag.toString().toInt(), uuid)
        Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it).popBackStack()
    }}


}