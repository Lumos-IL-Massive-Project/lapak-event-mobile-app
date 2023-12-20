package com.example.connectus.activities.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.connectus.R
import com.example.connectus.activities.changepassword.ChangePasswordActivity
import com.example.connectus.activities.changeprofile.ChangeProfileActivity
import com.example.connectus.activities.eoregistration.EventOrganizerRegistrationActivity
import com.example.connectus.activities.faq.FAQActivity
import com.example.connectus.activities.notificationsetting.NotificationSettingActivity
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.databinding.FragmentProfileBinding
import com.example.connectus.network.response.LoginData
import com.example.connectus.utils.AppPreferenceManager
import com.example.connectus.utils.Constants.BASE_URL
import com.example.connectus.utils.startDynamicActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null

    @Inject
    lateinit var appPreferenceManager: AppPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTopBar()
        initProfileSection()
        initButtonList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initTopBar() {
        binding?.customTopBar?.ivBack?.visibility = View.GONE
        binding?.customTopBar?.tvTopBarTitle?.text = "Profil"
    }

    private fun initProfileSection() {
        val authCredentials = appPreferenceManager.getAuthCredentials()
        val loginData = Gson().fromJson(authCredentials, LoginData::class.java)

        binding?.tvName?.text = loginData?.name
        Glide.with(this).load("${BASE_URL}${loginData?.profileImage}")
            .apply(RequestOptions().placeholder(R.drawable.img_blank_profile))
            .into(binding?.ciProfile!!)
    }

    private fun initButtonList() {
        binding?.signOutButton?.setOnClickListener {
            appPreferenceManager.removeAuthCredentials()
            startDynamicActivity(
                requireContext(),
                SignInActivity::class.java,
                R.anim.slide_right_in,
                R.anim.slide_right_out
            )
        }
        binding?.changeProfileSection?.setOnClickListener {
            startDynamicActivity(requireContext(), ChangeProfileActivity::class.java)
        }
        binding?.changePasswordSection?.setOnClickListener {
            startDynamicActivity(requireContext(), ChangePasswordActivity::class.java)
        }
        binding?.notificationSettingSection?.setOnClickListener {
            startDynamicActivity(requireContext(), NotificationSettingActivity::class.java)
        }
        binding?.FAQSection?.setOnClickListener {
            startDynamicActivity(requireContext(), FAQActivity::class.java)
        }
        binding?.registerEOSection?.setOnClickListener {
            startDynamicActivity(requireContext(), EventOrganizerRegistrationActivity::class.java)
        }
    }
}