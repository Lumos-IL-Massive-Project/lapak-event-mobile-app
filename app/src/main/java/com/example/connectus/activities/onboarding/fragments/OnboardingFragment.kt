package com.example.connectus.activities.onboarding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.connectus.activities.onboarding.models.PageData
import com.example.connectus.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private var binding: FragmentOnboardingBinding? = null
    private lateinit var page: PageData

    companion object {
        private const val ARG_PAGE_DATA = "pageData"

        fun newInstance(page: PageData): OnboardingFragment {
            val fragment = OnboardingFragment()
            val args = Bundle()
            args.putParcelable(ARG_PAGE_DATA, page)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            page = it.getParcelable(ARG_PAGE_DATA) ?: PageData("", "", 0)
        }
    }

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