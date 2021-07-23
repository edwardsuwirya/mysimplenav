package com.enigmacamp.mysimplenavigation.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import com.enigmacamp.mysimplenavigation.R

class SplashFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FragmentSplash", "onCreate: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FragmentSplash", "onDestroy: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment)
        }, 1000)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}