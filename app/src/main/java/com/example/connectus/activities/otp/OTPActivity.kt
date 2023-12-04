package com.example.connectus.activities.otp

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.connectus.BuildConfig
import com.example.connectus.activities.otp.viewmodels.OTPViewModel
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.databinding.ActivityOtpBinding
import com.example.connectus.network.ApiResult
import com.example.connectus.network.bodyrequest.RefreshOTPBody
import com.example.connectus.network.bodyrequest.VerifyOtpBody
import com.example.connectus.utils.GlobalPopup.dismissLoadingPopup
import com.example.connectus.utils.GlobalPopup.showConfirmationPopup
import com.example.connectus.utils.GlobalPopup.showLoadingPopup
import com.example.connectus.utils.GlobalPopup.showWarningPopup
import com.example.connectus.utils.resetActivity
import com.example.connectus.utils.startDynamicActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OTPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    private lateinit var otpCountDownTimer: CountDownTimer
    private lateinit var resendOtpCountDownTimer: CountDownTimer
    private val viewModel by viewModels<OTPViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVerifyOTPObserver()
        initRefreshOTPObserver()
        initForm()
        initResendOTPCode()
        initConfirmButton()
        initSignInButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        otpCountDownTimer.cancel()
    }

    private fun initVerifyOTPObserver() {
        viewModel.verifyOtpResult.observe(this, Observer { result ->
            when (result) {
                is ApiResult.Error -> {
                    dismissLoadingPopup()
                    showWarningPopup(this, layoutInflater, false, result.message.toString(), null)
                }

                is ApiResult.Loading -> {
                    showLoadingPopup(this, layoutInflater, true)
                }

                is ApiResult.Success -> {
                    dismissLoadingPopup()
                    showWarningPopup(this, layoutInflater, true, result.data.message.toString()) {
                        val emailToRegister = intent.getStringExtra(EMAIL_TO_REGISTER)
                        resetActivity(
                            this, SignInActivity::class.java, data = arrayOf(
                                Pair(EMAIL_TO_REGISTER, emailToRegister)
                            )
                        )
                    }
                }
            }
        })
    }

    private fun initRefreshOTPObserver() {
        viewModel.refreshOtpResult.observe(this, Observer { result ->
            when (result) {
                is ApiResult.Error -> {
                    dismissLoadingPopup()
                    showWarningPopup(this, layoutInflater, false, result.message.toString(), null)
                }

                is ApiResult.Loading -> {
                    showLoadingPopup(this, layoutInflater, true)
                }

                is ApiResult.Success -> {
                    otpCountDownTimer.cancel()

                    dismissLoadingPopup()
                    showWarningPopup(
                        this,
                        layoutInflater,
                        true,
                        result.data.message.toString(),
                        null
                    )

                    startOtpCountdown(300000) // 5 minutes
                    startResendOtpCountdown(60000) // 1 minute
                    binding.tvResendCode.isEnabled = false

                    if (BuildConfig.DEBUG) {
                        binding.pinView.setText(result.data.data?.otp)
                    }
                }
            }
        })
    }

    private fun initForm() {
        if (BuildConfig.DEBUG) {
            val otpCode = intent.getStringExtra(OTP_CODE)
            binding.pinView.setText(otpCode)
        }

        startOtpCountdown(300000) // 5 minutes
    }

    private fun initResendOTPCode() {
        binding.tvResendCode.isEnabled = false
        binding.tvResendCode.setOnClickListener {
            showConfirmationPopup(
                this,
                layoutInflater,
                "Apakah anda ingin mengirim ulang kode OTP?"
            ) {
                val emailToRegister = intent.getStringExtra(EMAIL_TO_REGISTER)
                viewModel.refreshOTP(RefreshOTPBody(emailToRegister!!))
            }
        }

        startResendOtpCountdown(60000) // 1 minute
    }

    private fun initConfirmButton() {
        binding.confirmBtn.setOnClickListener {
            val emailToRegister = intent.getStringExtra(EMAIL_TO_REGISTER)
            val otp = binding.pinView.text.toString()

            if (otp.isEmpty()) {
                showWarningPopup(this, layoutInflater, false, "Kode OTP harus diisi", null)
                return@setOnClickListener
            }

            viewModel.verifyOTP(VerifyOtpBody(emailToRegister!!, otp))
        }
    }

    private fun initSignInButton() {
        binding.tvSignInBtn.setOnClickListener {
            startDynamicActivity(this, SignInActivity::class.java)
        }
    }

    private fun startOtpCountdown(duration: Long) {
        otpCountDownTimer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)
                binding.tvCountdown.text = timeLeftFormatted
            }

            override fun onFinish() {
                binding.tvCountdown.text = "00:00"
            }
        }

        otpCountDownTimer.start()
    }

    private fun startResendOtpCountdown(duration: Long) {
        resendOtpCountDownTimer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val timeLeftFormatted = String.format("%02d", seconds)
                binding.tvResendCode.text = "Kirim ulang kode OTP? $timeLeftFormatted detik"
            }

            override fun onFinish() {
                binding.tvResendCode.text = "Kirim ulang kode OTP?"
                binding.tvResendCode.isEnabled = true
            }
        }

        resendOtpCountDownTimer.start()
    }

    companion object {
        private const val EMAIL_TO_REGISTER = "EMAIL_TO_REGISTER"
        private const val OTP_CODE = "OTP_CODE"
    }
}