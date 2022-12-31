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

class FragmentThird : Fragment() {

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
        val btn_frag = view.findViewById<Button>(R.id.btn_frag)

        val txtView = view.findViewById<TextView>(R.id.txt_frag)
        txtView.text = "Third Fragment"

        val bundle = this.arguments

        bundle?.let {
            it.getString("family")?.let { it1 -> Log.e("poppers", it1) }
        }

        btn_frag.setOnClickListener {
            val fm: FragmentManager = parentFragmentManager
            for (i in 0 until fm.backStackEntryCount){
                fm.popBackStack(fm.getBackStackEntryAt(i).id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }

    }
}