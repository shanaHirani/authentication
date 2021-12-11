package com.shana.authentication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shana.authentication.EventObserver
import com.shana.authentication.data.remoteDataSource.Resource
import com.shana.authentication.databinding.FragmentHomePageBinding
import com.shana.authentication.handleApiError
import com.shana.authentication.ui.logIn.LogInFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private val viewModel: HomePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomePageBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.apiError.observe(viewLifecycleOwner, EventObserver{
            handleApiError(it,viewModel){viewModel.getProfessorsInfo()}
        })

        viewModel.navigateToLogInFragment.observe(viewLifecycleOwner,{
            this.findNavController().navigate(
                HomePageFragmentDirections.actionHomePageFragmentToLogInFragment()
            )
        })
        return binding.root
    }


}