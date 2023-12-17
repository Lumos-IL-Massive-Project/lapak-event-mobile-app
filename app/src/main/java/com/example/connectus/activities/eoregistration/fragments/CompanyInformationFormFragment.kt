package com.example.connectus.activities.eoregistration.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.connectus.R
import com.example.connectus.databinding.FragmentCompanyInformationFormBinding

class CompanyInformationFormFragment : Fragment() {
    private var binding: FragmentCompanyInformationFormBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompanyInformationFormBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}