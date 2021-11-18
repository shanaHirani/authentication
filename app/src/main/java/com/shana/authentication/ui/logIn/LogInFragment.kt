package com.shana.authentication.ui.logIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shana.authentication.R
import com.shana.authentication.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {
    private lateinit var viewModel : LogInViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentLogInBinding.inflate(inflater)
        binding.lifecycleOwner = this
        //binding.viewModel = viewModel
        return binding.root
    }

}