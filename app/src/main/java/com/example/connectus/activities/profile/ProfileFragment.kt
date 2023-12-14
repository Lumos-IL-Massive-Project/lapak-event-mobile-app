package com.example.connectus.activities.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.connectus.R
import com.example.connectus.activities.changepassword.ChangePasswordActivity
import com.example.connectus.activities.changeprofile.ChangeProfileActivity
import com.example.connectus.activities.notificationsetting.NotificationSettingActivity
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.databinding.FragmentProfileBinding
import com.example.connectus.utils.AppPreferenceManager
import com.example.connectus.utils.startDynamicActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null

    @Inject
    lateinit var appPreferenceManager: AppPreferenceManager

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
    }
}