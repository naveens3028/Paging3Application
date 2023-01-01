package com.android.koinapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect

class FragmentSecond : Fragment() {

    lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fragment, container, false);
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val txtView = view.findViewById<TextView>(R.id.txt_frag)
        txtView.text = "Second Fragment"

        val bundle = this.arguments

        bundle?.let {
            it.getString("family")?.let { it1 -> Log.e("poppers", it1) }
        }

        navigateFragment(view)

        viewModel.getDataWithFlow()

        lifecycleScope.launchWhenCreated {
            viewModel.dataFlow.collect{
                Log.e("AsusFlow", it.toString())
            }
        }
    }

    private fun navigateFragment(view: View) {
        val btn_frag = view.findViewById<Button>(R.id.btn_frag)
        btn_frag.setOnClickListener {
            val firstFragment = FragmentThird()
            // create a FragmentManager
            val data = Bundle()
            data.putString("family", "Third")
            firstFragment.arguments = data
            val fm: FragmentManager = parentFragmentManager
            val fragmentTransaction: FragmentTransaction = fm.beginTransaction()
            fragmentTransaction.replace(R.id.frame_main, firstFragment)
            fragmentTransaction.addToBackStack("third")
            fragmentTransaction.commit() // save the changes
        }
    }
}