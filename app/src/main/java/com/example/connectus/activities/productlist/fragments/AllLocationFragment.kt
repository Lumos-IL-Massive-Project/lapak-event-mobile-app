package com.example.connectus.activities.productlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.R
import com.example.connectus.activities.productlist.adapters.RecyclerViewAllLocationAdapter
import com.example.connectus.activities.productlist.models.LocationData
import com.example.connectus.databinding.FragmentAllLocationBinding

class AllLocationFragment : Fragment() {
    private var _binding: FragmentAllLocationBinding? = null
    private val binding get() = _binding!!

    private val location = listOf(
        LocationData("Jakarta", false),
        LocationData("Depok", false),
        LocationData("Bogor", false),
        LocationData("Tanggerang", false),
        LocationData("Bekasi", false),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllLocationBinding.inflate(inflater, container, false)
        binding.rvCheckBoxLocation.layoutManager = LinearLayoutManager(context)
        binding.rvCheckBoxLocation.adapter = RecyclerViewAllLocationAdapter(location)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toMainFilterModal(binding.ivBackBtn)
        toMainFilterModal(binding.tvConfirmation)
    }

    private fun toMainFilterModal(component: View) {
        component.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer, MainFilterFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
