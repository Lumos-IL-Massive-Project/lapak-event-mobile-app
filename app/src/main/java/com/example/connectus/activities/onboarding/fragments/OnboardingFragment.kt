package com.example.connectus.activities.onboarding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.connectus.activities.onboarding.models.PageData
import com.example.connectus.databinding.FragmentOnboardingBinding

class OnboardingFragment(private val page: PageData) : Fragment() {
    private var binding: FragmentOnboardingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        binding?.tvOnboardingTitle?.text = page.title
        binding?.tvOnboardingDescription?.text = page.description
        binding?.ivOnboarding?.setImageResource(page.image)

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}