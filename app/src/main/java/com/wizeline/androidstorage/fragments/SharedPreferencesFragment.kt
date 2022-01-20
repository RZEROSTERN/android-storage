package com.wizeline.androidstorage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.wizeline.androidstorage.R
import com.wizeline.androidstorage.databinding.FragmentSharedPreferencesBinding

class SharedPreferencesFragment : Fragment() {
    lateinit var binding: FragmentSharedPreferencesBinding

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
}