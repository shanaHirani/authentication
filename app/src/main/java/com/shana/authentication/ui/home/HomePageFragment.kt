package com.shana.authentication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shana.authentication.R
import com.shana.authentication.databinding.FragmentHomePageBinding

class HomePageFragment : Fragment() {

    private lateinit var viewModel: HomePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomePageBinding.inflate(inflater)
        binding.lifecycleOwner = this
//        binding.viewModel = viewModel
        return binding.root
    }


}