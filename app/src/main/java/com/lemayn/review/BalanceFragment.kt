package com.lemayn.review

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * author: ly
 * date  : 2019/7/13 15:00
 * desc  :
 */
class BalanceFragment : Fragment() {

    companion object {
        fun getInstance(): BalanceFragment {
            return BalanceFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_balance, container, false)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        return view
    }

}