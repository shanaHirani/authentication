package com.shana.authentication.ui.logIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.shana.authentication.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint
import com.shana.authentication.remoteDataSource.Resource

@AndroidEntryPoint
class LogInFragment : Fragment() {

    private val viewModel: LogInViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLogInBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.logInResponse.observe(viewLifecycleOwner,{
            when(it){
                is Resource.Success ->{
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
                is Resource.Failure ->{
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })

        return binding.root
    }

}