package com.example.todoapps.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapps.R
import com.example.todoapps.databinding.FragmentCreateTodoBinding
import com.example.todoapps.viewmodel.DetailTodoViewModel
import com.google.android.material.textfield.TextInputEditText


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

        val txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
        val txtNotes = view.findViewById<TextView>(R.id.txtNotes)
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)

        txtJudulTodo.text = "Edit Todo"
        btnAdd.text = "Save Changes"

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        btnAdd.setOnClickListener {
            var radio = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            var radioButton = view.findViewById<RadioButton>(radio.checkedRadioButtonId)

            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
                radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }}
    }

    fun observeViewModel() {
        val txtTitle = view?.findViewById<TextInputEditText>(R.id.txtTitle)
        val txtNotes = view?.findViewById<TextInputEditText>(R.id.txtNotes)

        var radiolow = view?.findViewById<RadioButton>(R.id.radioLow)
        var radiomedium = view?.findViewById<RadioButton>(R.id.radioMedium)
        var radiohigh = view?.findViewById<RadioButton>(R.id.radioHigh)
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            txtTitle.setText(it.title)
            txtNotes.setText(it.notes)
        })

        when (it.priority) {
            1 -> radioLow.isChecked = true
            2 -> radioMedium.isChecked = true
            else -> radioHigh.isChecked = true
        }





}