package com.example.connectus.activities.changeprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.databinding.ActivityChangeProfileBinding
import com.example.connectus.network.response.LoginData
import com.example.connectus.utils.AppPreferenceManager
import com.example.connectus.utils.GlobalPopup.showConfirmationPopup
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChangeProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangeProfileBinding

    @Inject
    lateinit var appPreferenceManager: AppPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initForm()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Ubah Profil"
    }

    private fun initForm() {
        val authCredentials = appPreferenceManager.getAuthCredentials()
        val loginData = Gson().fromJson(authCredentials, LoginData::class.java)

        binding.etUsername.setText(loginData?.name)
        binding.etPhoneNumber.setText(loginData?.phoneNumber)
        binding.etEmail.setText(loginData?.email)

        binding.saveButton.setOnClickListener {
            showConfirmationPopup(this, layoutInflater, "Apakah anda yakin data sudah benar?") {
                finish()
            }
        }
    }
}