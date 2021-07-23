package com.enigmacamp.mysimplenavigation.ui.transaction

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enigmacamp.mysimplenavigation.ui.NavigationCommand
import com.enigmacamp.mysimplenavigation.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {

    private lateinit var binding: FragmentTransactionBinding
    private lateinit var viewModel: TransactionFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FragmentTransaction", "onCreate: ")
        initViewModel()
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("FragmentTransaction", "onBackPressed: ")
                    viewModel.doExit()
                }
            })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(TransactionFragmentViewModel::class.java)
    }

    private fun subscriber() {
        viewModel.navigationCommandLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NavigationCommand.To -> findNavController().navigate(it.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
                is NavigationCommand.BackTo -> findNavController().popBackStack(
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
        Log.d("FragmentTransaction", "onDestroy: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransactionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscriber()
        binding.apply {
            signOutTransactionbutton.setOnClickListener {
                setFragmentResult("INFO", bundleOf("username" to "edi"))
                viewModel.doSignOut()
//                Navigation.findNavController(view).navigateUp()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TransactionFragment()
    }
}