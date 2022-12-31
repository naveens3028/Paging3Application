package com.android.koinapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.android.koinapplication.model.ExampleJson2KtKotlin
import kotlinx.coroutines.flow.collectLatest

class FragmentFirst: Fragment() {

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
        recyclerViewAdapter = UserDataAdapter()
        recyclerPag.adapter = recyclerViewAdapter
    }
}