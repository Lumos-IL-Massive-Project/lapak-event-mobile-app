package com.example.connectus.activities.eoregistration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.connectus.databinding.FragmentEventOrganizerInformationFormBinding

class EventOrganizerInformationFormFragment : Fragment() {
    private var binding: FragmentEventOrganizerInformationFormBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventOrganizerInformationFormBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSelectCategoryButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initSelectCategoryButton() {
        binding?.btnSelectCategory?.setOnClickListener {
            val modalChooseCategoryFragment = ModalChooseCategoryFragment()
            modalChooseCategoryFragment.show(childFragmentManager, modalChooseCategoryFragment.tag)
        }
    }
}