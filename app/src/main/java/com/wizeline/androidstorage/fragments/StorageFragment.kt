package com.wizeline.androidstorage.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.wizeline.androidstorage.R
import com.wizeline.androidstorage.databinding.FragmentStorageBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.File

class StorageFragment : Fragment() {
    private lateinit var binding: FragmentStorageBinding
    private val fileName = "myJson.json"
    private var json = JSONObject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStorageBinding.inflate(inflater, container, false)

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        checkIfFileExists()

        binding.btnCreateFile.setOnClickListener { createFile() }
        binding.btnAddNode.setOnClickListener { addNodeToFile() }
        binding.btnWipeData.setOnClickListener { wipeData() }

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigate(R.id.action_storageFragment_to_menuFragment)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun checkIfFileExists() {
        val file = File(requireContext().filesDir, fileName)

        if(file.exists()) {
            binding.btnCreateFile.visibility = View.INVISIBLE
            refreshContent()
        }
    }

    private fun createFile() {
        val initialContent = "{}"
        requireContext().openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(initialContent.toByteArray())
        }
    }

    private fun addNodeToFile() {
        try {
            json.put(binding.etKey.text.toString(), binding.etValue.text.toString())

            requireContext().openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(json.toString().toByteArray())
            }

            refreshContent()
        } catch(e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun refreshContent() {
        val br = StringBuffer()

        requireContext().openFileInput(fileName).bufferedReader().forEachLine { it ->
            json = JSONObject(it)
            br.append(json)
        }

        binding.txtContent.text = br.toString()
    }

    private fun wipeData() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Wipe Data")
        builder.setMessage("Are you sure you want to wipe data?")

        builder.setPositiveButton(android.R.string.yes) { _, _ ->
            requireContext().deleteFile(fileName)
        }

        builder.setNegativeButton(android.R.string.no) { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }
}