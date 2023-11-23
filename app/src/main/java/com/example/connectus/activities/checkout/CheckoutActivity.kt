package com.example.connectus.activities.checkout

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.connectus.R
import com.example.connectus.activities.checkout.models.OrderSummaryData
import com.example.connectus.activities.checkout.viewmodels.CheckoutViewModel
import com.example.connectus.activities.paymentmethod.PaymentMethodActivity
import com.example.connectus.activities.productdetail.adapters.RecyclerViewPlanItemsAdapter
import com.example.connectus.activities.promo.PromoActivity
import com.example.connectus.databinding.ActivityCheckoutBinding
import com.example.connectus.utils.formatRupiah
import com.example.connectus.utils.startDynamicActivity
import java.util.Calendar

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var viewModel: CheckoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initTopBar()

        viewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
        viewModel.data.observe(this, Observer { data ->
            initVendorProfile(data)
            initProductDetails(data)
            initOrderSummary(data)
        })

        initServiceDateInput()
        initPromoButton()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Detail Pesanan"
    }

    private fun initVendorProfile(data: OrderSummaryData) {
        binding.tvEventOrganizerName.text = data.profile.name
        binding.tvEventOrganizerLocation.text = "${data.profile.city}, ${data.profile.province}"
        Glide.with(this).load(data.profile.imageUrl).into(binding.imgEventOrganizer)
    }

    private fun initProductDetails(data: OrderSummaryData) {
        binding.tvProductName.text = data.productName
        binding.tvPlanName.text = data.planName
        binding.tvPrice.text = formatRupiah(data.price)

        binding.rvPlanItems.adapter = RecyclerViewPlanItemsAdapter(data.planItems)
        binding.rvPlanItems.layoutManager = LinearLayoutManager(this)
    }

    private fun initServiceDateInput() {
        binding.etServiceDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            val datePickerDialog = DatePickerDialog(
                this,
                R.style.DatePicker,
                { _, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    binding.etServiceDate.setText(selectedDate)
                },
                year,
                month,
                day
            )

            datePickerDialog.setOnShowListener { dialog ->
                val okButton =
                    (dialog as DatePickerDialog).getButton(DatePickerDialog.BUTTON_POSITIVE)
                val cancelButton = dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)

                okButton.setTextColor(ContextCompat.getColor(this, R.color.purple_700))
                cancelButton.setTextColor(ContextCompat.getColor(this, R.color.purple_700))
            }
            datePickerDialog.show()
        }
    }

    private fun initPromoButton() {
        binding.selectPromoButton.setOnClickListener {
            startDynamicActivity(this, PromoActivity::class.java)
        }
    }

    private fun initOrderSummary(data: OrderSummaryData) {
        binding.tvSelectedPromo.text = data.promo
        binding.tvPromoDiscount.text = "${formatRupiah(data.promoDiscount)}"
        binding.tvSubtotal.text = "${formatRupiah(data.subtotal)}"

        binding.dpButton.text = "Bayar Dp ${formatRupiah(data.downPayment)}"
        binding.cashButton.text = "Bayar Lunas ${formatRupiah(data.totalPrice)}"

        binding.dpButton.setOnClickListener {
            startDynamicActivity(this, PaymentMethodActivity::class.java)
        }
        binding.cashButton.setOnClickListener {
            startDynamicActivity(this, PaymentMethodActivity::class.java)
        }
    }
}