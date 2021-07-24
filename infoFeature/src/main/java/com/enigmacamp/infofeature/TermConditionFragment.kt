package com.enigmacamp.infofeature

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enigmacamp.navigation.NavigationCommand
import com.enigmacamp.infofeature.databinding.FragmentTermConditionBinding


class TermConditionFragment : Fragment() {
    private lateinit var binding: FragmentTermConditionBinding
    private lateinit var viewModel: TermConditionFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        Log.d("FragmentTermCondition", "onCreate: ")
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("FragmentTermCondition", "onBackPressed: ")
                    viewModel.doExit()
                }
            })
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(TermConditionFragmentViewModel::class.java)
    }

    private fun subscriber() {
        viewModel.navigationCommandLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NavigationCommand.To -> findNavController().navigate(it.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
                is NavigationCommand.ToRoot -> requireActivity().finish()
                else -> {
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FragmentTermCondition", "onDestroy: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTermConditionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscriber()
        binding.apply {
            signOutButton.setOnClickListener {
                viewModel.doExit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TermConditionFragment()
    }
}