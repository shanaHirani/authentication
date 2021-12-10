package com.shana.authentication.ui.logIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shana.authentication.EventObserver
import com.shana.authentication.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint
import com.shana.authentication.data.remoteDataSource.Resource
import com.shana.authentication.handleApiError

@AndroidEntryPoint
class LogInFragment : Fragment() {

    private val viewModel: LogInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        viewModel.accessToken.observe(viewLifecycleOwner, {
//            if (it != null)
//                this.findNavController().navigate(
//                    LogInFragmentDirections.actionLogInFragmentToHomePageFragment()
//                )
//        })

        val binding = FragmentLogInBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.navigateToHomeFragment.observe(viewLifecycleOwner, EventObserver {
            this.findNavController().navigate(
                LogInFragmentDirections.actionLogInFragmentToHomePageFragment()
            )
        })

        viewModel.apiError.observe(viewLifecycleOwner,EventObserver{
            handleApiError(it){viewModel.logIn()}
        })

        return binding.root
    }

}