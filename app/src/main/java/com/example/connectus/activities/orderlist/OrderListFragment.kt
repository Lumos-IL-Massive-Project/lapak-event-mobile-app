package com.example.connectus.activities.orderlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.connectus.databinding.FragmentOrderListBinding

class OrderListFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentOrderListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}