package com.enigmacamp.mysimplenavigation.ui.aboutUs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enigmacamp.mysimplenavigation.databinding.FragmentAboutUsBinding
import com.enigmacamp.navigation.NavigationCommand

class AboutUsFragment : Fragment() {
    private lateinit var binding: FragmentAboutUsBinding
    private lateinit var viewModel: AboutUsFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        Log.d("FragmentAboutUs", "onCreate: ")
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("FragmentAboutUs", "onBackPressed: ")
                    viewModel.doExit()
                }
            })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AboutUsFragmentViewModel::class.java)
    }

    private fun subscriber() {
        viewModel.navigationCommandLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is com.enigmacamp.navigation.NavigationCommand.To -> findNavController().navigate(it.directions)
                is com.enigmacamp.navigation.NavigationCommand.Back -> findNavController().popBackStack()
                is com.enigmacamp.navigation.NavigationCommand.BackTo -> findNavController().popBackStack(
                    it.destinationId,
                    true
                )
                else -> {
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FragmentAboutUs", "onDestroy: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutUsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscriber()
        binding.apply {
            goButton.setOnClickListener {
                viewModel.doSignOut()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AboutUsFragment()
    }
}