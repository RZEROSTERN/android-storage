package com.wizeline.androidstorage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.wizeline.androidstorage.R
import com.wizeline.androidstorage.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    lateinit var binding : FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        binding.btnStorage.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_storageFragment)
        }

        binding.btnSharedPreferences.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_sharedPreferencesFragment)
        }

        binding.btnRoom.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_roomFragment)
        }

        return binding.root
    }
}