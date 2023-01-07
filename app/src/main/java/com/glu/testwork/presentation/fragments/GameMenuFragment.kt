package com.glu.testwork.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.glu.testwork.R
import com.glu.testwork.databinding.FragmentGameMenuBinding


class GameMenuFragment : Fragment() {
    lateinit var binding: FragmentGameMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openNewsFragment()
    }

    /**
     * Слушатели кликов и открытие по ним новых фрагментов
     */
    private fun openNewsFragment(){
        binding.btGame.setOnClickListener {
            findNavController().navigate(R.id.action_gameMenuFragment_to_gameFragment)
        }
        binding.btRecord.setOnClickListener {
            findNavController().navigate(R.id.action_gameMenuFragment_to_recordFragment)
        }
        binding.btSetting.setOnClickListener {
            findNavController().navigate(R.id.action_gameMenuFragment_to_settingFragment)
        }
        binding.btInstructions.setOnClickListener {
            findNavController().navigate(R.id.action_gameMenuFragment_to_instructionsFragment)
        }
    }

}