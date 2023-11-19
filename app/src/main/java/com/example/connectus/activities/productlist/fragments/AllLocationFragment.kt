package com.example.connectus.activities.productlist.fragments

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Identity
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.R
import com.example.connectus.activities.productlist.adapters.RecyclerViewLocationAdapter
import com.example.connectus.activities.search.models.LocationData
import com.example.connectus.databinding.FragmentAllLocationBinding

class AllLocationFragment : Fragment() {
    private var _binding: FragmentAllLocationBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewLocationAdapter
    private val location = listOf<LocationData>(
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
        // Inflate the layout for this fragment
        _binding = FragmentAllLocationBinding.inflate(inflater, container, false)
        val rootView = binding.root
        recyclerView = rootView.findViewById(R.id.rvCheckBoxLocation)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RecyclerViewLocationAdapter(location)
        recyclerView.adapter = adapter
        return rootView
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
