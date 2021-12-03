package com.shana.authentication.ui.logIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shana.authentication.data.UserPreferences
import com.shana.authentication.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint
import com.shana.authentication.data.remoteDataSource.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LogInFragment : Fragment() {

    private val viewModel: LogInViewModel by viewModels()

    @Inject
    lateinit var userPreferences: UserPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLogInBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val navController = this.findNavController()

        userPreferences.accessToken.asLiveData().observe(viewLifecycleOwner, {
            if (it != null)
                this.findNavController().navigate(
                    LogInFragmentDirections.actionLogInFragmentToHomePageFragment()
                )
        })

        viewModel.logInResponse.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    viewModel.saveAuthToken(it.value.token)
                    navController.navigate(
                        LogInFragmentDirections.actionLogInFragmentToHomePageFragment()
                    )
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })

        return binding.root
    }

}