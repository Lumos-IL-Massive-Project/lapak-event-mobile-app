package com.example.connectus.activities.paymentmethod

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.connectus.activities.instantpayment.InstantPaymentActivity
import com.example.connectus.activities.paymentbanktransfer.PaymentBankTransferActivity
import com.example.connectus.activities.paymentcreditcard.PaymentCreditCardActivity
import com.example.connectus.activities.paymentmethod.adapters.ExpandablePaymentMethodListAdapter
import com.example.connectus.activities.paymentmethod.models.PaymentData
import com.example.connectus.activities.paymentmethod.models.PaymentMethodData
import com.example.connectus.activities.paymentmethod.viewmodels.PaymentMethodViewModel
import com.example.connectus.activities.paymentvirtualaccount.PaymentVirtualAccountActivity
import com.example.connectus.databinding.ActivityPaymentMethodBinding
import com.example.connectus.databinding.GlobalConfirmationPopupBinding
import com.example.connectus.utils.startDynamicActivity

class PaymentMethodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentMethodBinding
    private lateinit var viewModel: PaymentMethodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initTopBar()

        viewModel = ViewModelProvider(this)[PaymentMethodViewModel::class.java]
        viewModel.data.observe(this, Observer { data ->
            initPaymentMethodList(data)
        })
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Metode Pembayaran"
    }

    private fun initPaymentMethodList(data: List<PaymentMethodData>) {
        val listAdapter = ExpandablePaymentMethodListAdapter(this, data)
        binding.expandableListView.setAdapter(listAdapter)

        binding.expandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val clickedItem = listAdapter.getChild(groupPosition, childPosition)
            selectPaymentAction(clickedItem)
            true
        }
    }

    private fun selectPaymentAction(data: PaymentData) {
        val dialog = Dialog(this)
        val confirmationPopupBinding = GlobalConfirmationPopupBinding.inflate(layoutInflater)
        dialog.setContentView(confirmationPopupBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        confirmationPopupBinding.btnClose.setOnClickListener {
            dialog.dismiss()
        }
        confirmationPopupBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        confirmationPopupBinding.btnConfirm.setOnClickListener {
            when (data.code) {
                "credit_card" -> {
                    dialog.dismiss()
                    startDynamicActivity(this, PaymentCreditCardActivity::class.java)
                }
                "manual_transfer" -> {
                    dialog.dismiss()
                    startDynamicActivity(this, PaymentBankTransferActivity::class.java)
                }
                "virtual_account" -> {
                    dialog.dismiss()
                    startDynamicActivity(this, PaymentVirtualAccountActivity::class.java)
                }
                "instant_payment" -> {
                    dialog.dismiss()
                    startDynamicActivity(this, InstantPaymentActivity::class.java, data = arrayOf(
                        Pair("KEY_HEADER_TITLE", data.name)
                    ))
                }
            }
        }
    }
}