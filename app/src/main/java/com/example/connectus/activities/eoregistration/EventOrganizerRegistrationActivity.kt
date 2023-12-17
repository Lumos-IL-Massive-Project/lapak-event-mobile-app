package com.example.connectus.activities.eoregistration

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.connectus.R
import com.example.connectus.activities.eoregistration.fragments.AdditionalDocumentFormFragment
import com.example.connectus.activities.eoregistration.fragments.CompanyInformationFormFragment
import com.example.connectus.activities.eoregistration.fragments.EventOrganizerInformationFormFragment
import com.example.connectus.activities.eoregistration.fragments.LocationFormFragment
import com.example.connectus.activities.eoregistration.fragments.SocialMediaFormFragment
import com.example.connectus.databinding.ActivityEventOrganizerRegistrationBinding
import com.example.connectus.databinding.GlobalConfirmationPopupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventOrganizerRegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventOrganizerRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventOrganizerRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        initTopBar()
        replaceFragment(CompanyInformationFormFragment())
        initBackStepButton()
        initContinueStepButton()
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            showConfirmationDialog("Apakah anda yakin tidak ingin menyelesaikan pengisian form?") {
                finish()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            showConfirmationDialog("Apakah anda yakin tidak ingin menyelesaikan pengisian form?") {
                finish()
            }
        }
        binding.customTopBar.tvTopBarTitle.text = "Daftar Sebagai Event Organizer"
    }

    @SuppressLint("SetTextI18n")
    private fun initBackStepButton() {
        binding.btnBackStep.setOnClickListener {
            when (binding.tvStepTitle.text) {
                "Langkah 2 dari 5" -> {
                    replaceFragment(CompanyInformationFormFragment())
                    animateFormProgress(40, 20, "Langkah 1 dari 5")
                    binding.btnBackStep.visibility = View.GONE
                }

                "Langkah 3 dari 5" -> {
                    replaceFragment(EventOrganizerInformationFormFragment())
                    animateFormProgress(60, 40, "Langkah 2 dari 5")
                }

                "Langkah 4 dari 5" -> {
                    replaceFragment(LocationFormFragment())
                    animateFormProgress(80, 60, "Langkah 3 dari 5")
                }

                "Langkah 5 dari 5" -> {
                    replaceFragment(SocialMediaFormFragment())
                    animateFormProgress(100, 80, "Langkah 4 dari 5")
                    binding.btnContinueStep.text = "Lanjutkan"
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initContinueStepButton() {
        binding.btnContinueStep.setOnClickListener {
            when (binding.tvStepTitle.text) {
                "Langkah 1 dari 5" -> {
                    replaceFragment(EventOrganizerInformationFormFragment())
                    binding.btnBackStep.visibility = View.VISIBLE
                    animateFormProgress(20, 40, "Langkah 2 dari 5")
                }

                "Langkah 2 dari 5" -> {
                    replaceFragment(LocationFormFragment())
                    animateFormProgress(40, 60, "Langkah 3 dari 5")
                }

                "Langkah 3 dari 5" -> {
                    replaceFragment(SocialMediaFormFragment())
                    animateFormProgress(60, 80, "Langkah 4 dari 5")
                }

                "Langkah 4 dari 5" -> {
                    replaceFragment(AdditionalDocumentFormFragment())
                    animateFormProgress(80, 100, "Langkah 5 dari 5")
                    binding.btnContinueStep.text = "Daftar"
                }

                "Langkah 5 dari 5" -> {
                    showConfirmationDialog("Apakah anda yakin data sudah benar?") {
                        finish()
                    }
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayoutContainer, fragment)
            commit()
        }
    }

    private fun animateFormProgress(startProgress: Int, endProgress: Int, progressLabel: String) {
        binding.formProgressBar.progress = endProgress
        binding.tvStepTitle.text = progressLabel

        val progressAnimator =
            ObjectAnimator.ofInt(binding.formProgressBar, "progress", startProgress, endProgress)
        progressAnimator.duration = 300

        progressAnimator.start()
    }

    private fun showConfirmationDialog(warningMessage: String, callback: (() -> Unit)) {
        val dialog = Dialog(this)
        val confirmationPopupBinding = GlobalConfirmationPopupBinding.inflate(layoutInflater)
        dialog.setContentView(confirmationPopupBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        confirmationPopupBinding.tvWarningTitle.text = warningMessage
        confirmationPopupBinding.btnClose.setOnClickListener {
            dialog.dismiss()
        }
        confirmationPopupBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        confirmationPopupBinding.btnConfirm.setOnClickListener {
            dialog.dismiss()
            callback.invoke()
        }
    }
}