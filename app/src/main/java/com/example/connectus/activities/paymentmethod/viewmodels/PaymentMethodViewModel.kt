package com.example.connectus.activities.paymentmethod.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connectus.R
import com.example.connectus.activities.paymentmethod.models.PaymentData
import com.example.connectus.activities.paymentmethod.models.PaymentMethodData

class PaymentMethodViewModel : ViewModel() {
    private val _data = MutableLiveData<List<PaymentMethodData>>()
    val data: LiveData<List<PaymentMethodData>> get() = _data

    init {
        val creditCardPayments = listOf(
            PaymentData("Visa", R.drawable.ic_visa_24, "credit_card"),
            PaymentData("Master Card", R.drawable.ic_mastercard2_24, "credit_card"),
            PaymentData("JCB", R.drawable.ic_jcb_24, "credit_card"),
            PaymentData("American Express", R.drawable.ic_american_express_24, "credit_card"),
        )

        val bankTransferPayment = listOf(
            PaymentData("BRI", R.drawable.ic_bri_24, "manual_transfer"),
            PaymentData("Mandiri", R.drawable.ic_mandiri_24, "manual_transfer"),
            PaymentData("BNI", R.drawable.ic_bni_24, "manual_transfer"),
            PaymentData("BCA", R.drawable.ic_bca_24, "manual_transfer"),
        )

        val virtualAccountPayment = listOf(
            PaymentData("BRI", R.drawable.ic_bri_24, "virtual_account"),
            PaymentData("Mandiri", R.drawable.ic_mandiri_24, "virtual_account"),
            PaymentData("BNI", R.drawable.ic_bni_24, "virtual_account"),
            PaymentData("BCA", R.drawable.ic_bca_24, "virtual_account"),
        )

        val instantPayment = listOf(
            PaymentData("GoPay", R.drawable.ic_gopay_24, "instant_payment"),
            PaymentData("GoPay Later", R.drawable.ic_gopay_later_24, "instant_payment"),
            PaymentData("OVO", R.drawable.ic_ovo_24, "instant_payment"),
            PaymentData("LinkAja", R.drawable.ic_linkaja_24, "instant_payment"),
        )

        val mockData = listOf(
            PaymentMethodData("Kartu Kredit", R.drawable.ic_credit_card_24, creditCardPayments),
            PaymentMethodData("Transfer Bank", R.drawable.ic_transfer_bank_24, bankTransferPayment),
            PaymentMethodData("Virtual Account", R.drawable.ic_virtual_account_24, virtualAccountPayment),
            PaymentMethodData("Pembayaran Instant", R.drawable.ic_instant_payment_24, instantPayment),
        )

        _data.value = mockData
    }
}