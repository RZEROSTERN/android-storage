package com.wizeline.androidstorage.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.wizeline.androidstorage.R
import com.wizeline.androidstorage.databinding.FragmentSharedPreferencesBinding

class SharedPreferencesFragment : Fragment() {
    lateinit var binding: FragmentSharedPreferencesBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSharedPreferencesBinding.inflate(inflater, container, false)

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        refreshContent()

        binding.btnAddNode.setOnClickListener {
            if(binding.etKey.text.isNotEmpty() && binding.etValue.text.isNotEmpty()) {
                saveNodeToSharedPreferences()
            } else {
                Toast.makeText(requireContext(), "You must deliver me orders!", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnWipeData.setOnClickListener { wipeData() }

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigate(R.id.action_sharedPreferencesFragment_to_menuFragment)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun saveNodeToSharedPreferences() {
        with(sharedPreferences.edit()) {
            putString(binding.etKey.text.toString(), binding.etValue.text.toString())
            apply()
        }

        refreshContent()
    }

    private fun refreshContent() {
        binding.txtContent.text = sharedPreferences.all.entries.toString()
    }
    
    private fun wipeData() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Wipe Data")
        builder.setMessage("Are you sure you want to wipe shared preferences?")

        builder.setPositiveButton(android.R.string.yes) { _, _ ->
            with(sharedPreferences.edit()){
                clear()
                apply()
            }

            binding.txtContent.text = "Wiped Data!"
        }

        builder.setNegativeButton(android.R.string.no) { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }
}