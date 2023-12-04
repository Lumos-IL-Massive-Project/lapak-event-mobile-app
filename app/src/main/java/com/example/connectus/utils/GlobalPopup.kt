package com.example.connectus.utils

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.connectus.R
import com.example.connectus.databinding.GlobalLoadingPopupBinding
import com.example.connectus.databinding.GlobalWarningPopupBinding

object GlobalPopup {
    private var loadingDialog: Dialog? = null

    fun showWarningPopup(
        context: Context,
        layoutInflater: LayoutInflater,
        success: Boolean,
        message: String,
        callback: (() -> Unit)?
    ) {
        val dialog = Dialog(context)
        val globalWarningPopupBinding = GlobalWarningPopupBinding.inflate(layoutInflater)
        dialog.setContentView(globalWarningPopupBinding.root)
        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val percent = 90.toFloat() / 100
            val dm = Resources.getSystem().displayMetrics
            val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
            val percentWidth = rect.width() * percent
            setLayout(
                percentWidth.toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        dialog.setCancelable(false)

        globalWarningPopupBinding.apply {
            if (success) {
                tvWarningTitle.text = "Berhasil"
                ivWarning.setImageResource(R.drawable.ic_success)
            } else {
                tvWarningTitle.text = "Gagal"
                ivWarning.setImageResource(R.drawable.ic_failed)
            }

            tvWarningMessage.text = message
        }

        dialog.show()

        Handler().postDelayed({
            dialog.dismiss()
            callback?.invoke()
        }, 3000)
    }

    fun showLoadingPopup(context: Context, layoutInflater: LayoutInflater, show: Boolean) {
        if (show) {
            loadingDialog = Dialog(context)
            val globalLoadingPopupBinding = GlobalLoadingPopupBinding.inflate(layoutInflater)
            loadingDialog?.setContentView(globalLoadingPopupBinding.root)
            loadingDialog?.setCancelable(false)

            val lottieAnimationView = globalLoadingPopupBinding.lottieAnimationView
            lottieAnimationView.setAnimation(R.raw.loading_animation)

            loadingDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            loadingDialog?.window?.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            loadingDialog?.show()
        } else {
            dismissLoadingPopup()
        }
    }

    fun dismissLoadingPopup() {
        loadingDialog?.dismiss()
    }
}
