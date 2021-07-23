package com.enigmacamp.mysimplenavigation.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enigmacamp.mysimplenavigation.ui.NavigationCommand
import com.enigmacamp.mysimplenavigation.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FragmentLogin", "onCreate: ")
        initViewModel()
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("FragmentLogin", "onBackPressed: ")
                    viewModel.doExit()
                }
            })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LoginFragmentViewModel::class.java)
    }

    private fun subscriber() {
        viewModel.navigationCommandLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NavigationCommand.To -> findNavController().navigate(it.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
                is NavigationCommand.ToRoot -> requireActivity().finish()
                is NavigationCommand.DeepLink -> findNavController().navigate(it.destinationDeep)
                else -> {
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FragmentLogin", "onDestroy: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscriber()

        setFragmentResultListener("INFO") { requestKey, bundle ->
            Log.d("Login", "onViewCreated: ${bundle.getString("username")}")
        }
        binding.apply {
            loginButton.setOnClickListener {
                viewModel.doAuthenticate()
            }
            termConditionButton.setOnClickListener {
                viewModel.doNavigateTermCondition()
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}