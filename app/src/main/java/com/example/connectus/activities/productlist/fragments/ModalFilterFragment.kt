package com.example.connectus.activities.productlist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.connectus.R
import com.example.connectus.databinding.FragmentModalFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalFilterFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentModalFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, MainFilterFragment())
        fragmentTransaction.commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModalFilterBinding.inflate(inflater, container, false)
        return binding.root
    }
}