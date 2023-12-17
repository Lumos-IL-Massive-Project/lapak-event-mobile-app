package com.example.connectus.activities.eoregistration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.connectus.databinding.FragmentLocationFormBinding

class LocationFormFragment : Fragment() {
    private var binding: FragmentLocationFormBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationFormBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProvinceButton()
        initCityButton()
        initPostalCodeButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initProvinceButton() {
        binding?.btnSelectProvince?.setOnClickListener {
            val modalChooseProvinceFragment = ModalChooseProvinceFragment()
            modalChooseProvinceFragment.show(childFragmentManager, modalChooseProvinceFragment.tag)
        }
    }

    private fun initCityButton() {
        binding?.btnSelectCity?.setOnClickListener {
            val modalChooseCityFragment = ModalChooseCityFragment()
            modalChooseCityFragment.show(childFragmentManager, modalChooseCityFragment.tag)
        }
    }

    private fun initPostalCodeButton() {
        binding?.btnSelectPostalCode?.setOnClickListener {
            val modalChoosePostalCodeFragment = ModalChoosePostalCodeFragment()
            modalChoosePostalCodeFragment.show(
                childFragmentManager,
                modalChoosePostalCodeFragment.tag
            )
        }
    }
}