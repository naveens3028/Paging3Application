package com.android.koinapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.android.koinapplication.model.ExampleJson2KtKotlin
import kotlinx.coroutines.flow.collectLatest

class FragmentFirst: Fragment() , OnProceedClick{

    lateinit var viewModel: MainViewModel
    lateinit var recyclerViewAdapter: UserDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerPag = view.findViewById<RecyclerView>(R.id.recyclerPag)
        callAdapter(recyclerPag)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    private fun callAdapter(recyclerPag: RecyclerView) {
        recyclerViewAdapter = UserDataAdapter(this)
        recyclerPag.adapter = recyclerViewAdapter
    }

    override fun onNextClicked() {
        val firstFragment = FragmentSecond()
        // create a FragmentManager
        val data = Bundle()
        data.putString("family", "Second")
        firstFragment.arguments = data
        val fm: FragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.frame_main, firstFragment)
        fragmentTransaction.addToBackStack("Second")
        fragmentTransaction.commit() // save the changes
    }
}