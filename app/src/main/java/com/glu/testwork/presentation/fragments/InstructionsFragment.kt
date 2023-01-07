package com.glu.testwork.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.glu.testwork.R
import com.glu.testwork.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {
    lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInstructionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btExit.setOnClickListener {
            findNavController().navigate(R.id.action_instructionsFragment_to_gameMenuFragment)
        }
    }
}