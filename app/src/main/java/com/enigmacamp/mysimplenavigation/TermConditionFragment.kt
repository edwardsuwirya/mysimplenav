package com.enigmacamp.mysimplenavigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class TermConditionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FragmentTermCondition", "onCreate: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FragmentTermCondition", "onDestroy: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_term_condition, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = TermConditionFragment()
    }
}