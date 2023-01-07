package com.glu.testwork.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.glu.testwork.R
import com.glu.testwork.databinding.FragmentRecordBinding
import com.glu.testwork.di.DaggerApplicationsComponent
import com.glu.testwork.presentation.GameViewModel
import com.glu.testwork.presentation.GameViewModelFactory
import javax.inject.Inject


class RecordFragment : Fragment() {
    lateinit var binding: FragmentRecordBinding
    @Inject
    lateinit var viewModelFactory: GameViewModelFactory
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[GameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerApplicationsComponent.create().inject(this)
        binding.btExit.setOnClickListener {
            findNavController().navigate(R.id.action_recordFragment_to_gameMenuFragment)
        }
        setTextRecord()
    }

    /**
     * Устанавливаю текст в топ 3
     */
    private fun setTextRecord() {
        binding.tvRecord1.text = viewModel.firstPlace.toString()
        binding.tvRecord2.text = viewModel.secondPlace.toString()
        binding.tvRecord3.text = viewModel.thirdPlace.toString()
    }
}