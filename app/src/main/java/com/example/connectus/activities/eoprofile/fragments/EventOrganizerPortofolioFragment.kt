package com.example.connectus.activities.eoprofile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.connectus.activities.eoprofile.adapters.RecyclerViewPortofolioAdapter
import com.example.connectus.activities.eoprofile.models.PortofolioData
import com.example.connectus.databinding.FragmentEventOrganizerPortofolioBinding

class EventOrganizerPortofolioFragment : Fragment() {
    private var binding: FragmentEventOrganizerPortofolioBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventOrganizerPortofolioBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun init() {
        val data = listOf(
            PortofolioData("https://images.unsplash.com/photo-1515934751635-c81c6bc9a2d8?q=80&w=3570&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            PortofolioData("https://images.unsplash.com/photo-1520854221256-17451cc331bf?q=80&w=3570&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            PortofolioData("https://images.unsplash.com/photo-1519741497674-611481863552?q=80&w=3570&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            PortofolioData("https://images.unsplash.com/photo-1515934751635-c81c6bc9a2d8?q=80&w=3570&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            PortofolioData("https://images.unsplash.com/photo-1520854221256-17451cc331bf?q=80&w=3570&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            PortofolioData("https://images.unsplash.com/photo-1519741497674-611481863552?q=80&w=3570&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            PortofolioData("https://images.unsplash.com/photo-1515934751635-c81c6bc9a2d8?q=80&w=3570&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            PortofolioData("https://images.unsplash.com/photo-1520854221256-17451cc331bf?q=80&w=3570&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            PortofolioData("https://images.unsplash.com/photo-1519741497674-611481863552?q=80&w=3570&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            PortofolioData("https://images.unsplash.com/photo-1515934751635-c81c6bc9a2d8?q=80&w=3570&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            PortofolioData("https://images.unsplash.com/photo-1520854221256-17451cc331bf?q=80&w=3570&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            PortofolioData("https://images.unsplash.com/photo-1519741497674-611481863552?q=80&w=3570&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        )

        binding?.rvPortofolioList?.adapter = RecyclerViewPortofolioAdapter(data)
        binding?.rvPortofolioList?.layoutManager = GridLayoutManager(requireContext(), 3)
    }
}