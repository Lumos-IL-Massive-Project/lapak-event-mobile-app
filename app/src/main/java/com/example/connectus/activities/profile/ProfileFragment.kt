package com.example.connectus.activities.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.databinding.FragmentProfileBinding
import com.example.connectus.utils.startDynamicActivity

class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        initTopBar()
        initLayout()
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initTopBar() {
        binding?.customTopBar?.ivBack?.visibility = View.GONE
        binding?.customTopBar?.tvTopBarTitle?.text = "Profil"
    }

    private fun initLayout() {
        binding?.signOutButton?.setOnClickListener {
            startDynamicActivity(requireContext(), SignInActivity::class.java)
        }
    }
}