package com.wizeline.androidstorage.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wizeline.androidstorage.MainApplication
import com.wizeline.androidstorage.R
import com.wizeline.androidstorage.data.model.PersonalData
import com.wizeline.androidstorage.databinding.FragmentRoomBinding

class RoomFragment : Fragment() {
    lateinit var binding: FragmentRoomBinding
    private val viewModel: RoomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomBinding.inflate(inflater, container, false)

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        viewModel.initRoom(requireActivity().application as MainApplication)

        addListeners()

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigate(R.id.action_roomFragment_to_menuFragment)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun addListeners() {
        viewModel.insertResult.observe(viewLifecycleOwner, {
            if(it > 0) {
                Toast.makeText(requireContext(), "Data inserted!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Error during data insert!", Toast.LENGTH_LONG).show()
            }

            clearFields()
        })

        viewModel.personalData.observe(viewLifecycleOwner, {
            binding.etPhone.setText(it.phone)
            binding.etName.setText(it.first_name)
            binding.etLastName.setText(it.last_name)
        })

        viewModel.deleteResult.observe(viewLifecycleOwner, {
            if(it > 0) {
                Toast.makeText(requireContext(), "Data deleted!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Error during data delete!", Toast.LENGTH_LONG).show()
            }

            clearFields()
        })

        binding.btnInsert.setOnClickListener {
            if(validateForm()) {
                val personalData = PersonalData(
                    0, binding.etEmail.text.toString(),
                    binding.etName.text.toString(), binding.etLastName.text.toString(),
                    binding.etPhone.text.toString()
                )

                viewModel.insertData(personalData)
            } else {
                Toast.makeText(requireContext(), "You must give me data.", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnDelete.setOnClickListener {
            if(binding.etEmail.text.isNotBlank()) {
                viewModel.deleteData(binding.etEmail.text.toString())
            } else {
                Toast.makeText(requireContext(), "Please, give me the email.", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnSelect.setOnClickListener {
            if(binding.etEmail.text.isNotBlank()) {
                viewModel.getData(binding.etEmail.text.toString())
            } else {
                Toast.makeText(requireContext(), "Please, give me the email.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        return binding.etEmail.text.isNotBlank() && binding.etName.text.isNotBlank() &&
                binding.etLastName.text.isNotBlank() && binding.etPhone.text.isNotBlank()
    }

    private fun clearFields() {
        binding.etEmail.text.clear()
        binding.etName.text.clear()
        binding.etLastName.text.clear()
        binding.etPhone.text.clear()
    }
}