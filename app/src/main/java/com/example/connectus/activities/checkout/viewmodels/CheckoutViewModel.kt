package com.example.connectus.activities.checkout.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connectus.activities.checkout.models.OrderSummaryData
import com.example.connectus.activities.productdetail.models.PlanDetailItemListData
import com.example.connectus.activities.productdetail.models.VendorProfileData

class CheckoutViewModel: ViewModel() {
    private val _data = MutableLiveData<OrderSummaryData>()
    val data: LiveData<OrderSummaryData> get() = _data

    init {
        val vendorProfile = VendorProfileData(
            1,
            "Grand Galas EO",
            "Jakarta Selatan",
            "DKI Jakarta",
            "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc="
        )

        val planItemList: List<PlanDetailItemListData> = listOf(
            PlanDetailItemListData("Basic product launching planning consultation."),
            PlanDetailItemListData("Venue booking and setup."),
            PlanDetailItemListData("Audio-visual equipment for presentations."),
            PlanDetailItemListData("Catering for up to 50 attendees."),
            PlanDetailItemListData("Light refreshments for attendees."),
            PlanDetailItemListData("Event coordination on the day.")
        )


        val mockData = OrderSummaryData(
            vendorProfile,
            "Product Launching for Company",
            28920000,
            "Silver",
            planItemList,
            "Promo Spesial Oktober",
            1446000,
            28920000,
            5000000,
            28920000
        )

        _data.value = mockData
    }
}