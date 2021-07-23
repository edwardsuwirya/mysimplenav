package com.enigmacamp.mysimplenavigation.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.enigmacamp.mysimplenavigation.ui.NavigationCommand
import com.enigmacamp.mysimplenavigation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel
    private val args: HomeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FragmentHome", "onCreate: ")
        initViewModel()
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("FragmentHome", "onBackPressed: ")
                    viewModel.doExit()
                }
            })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
    }

    private fun subscriber() {
        viewModel.navigationCommandLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NavigationCommand.To -> findNavController().navigate(it.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
                else -> {
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FragmentHome", "onDestroy: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscriber()
        Log.d("FragmentHome", "onViewCreated: ${args.userName}")
        binding.apply {
            textView2.text = "Home Screen ${args.userName}"
            profileButton.setOnClickListener {
                viewModel.doNavigateProfile()
            }

            transactionButton.setOnClickListener {
                viewModel.doNavigateTransaction()
            }

            aboutUsButton.setOnClickListener {
                viewModel.doNavigateAboutUs()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}