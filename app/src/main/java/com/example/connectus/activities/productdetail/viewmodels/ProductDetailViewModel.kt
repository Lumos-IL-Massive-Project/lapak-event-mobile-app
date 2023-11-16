package com.example.connectus.activities.productdetail.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connectus.activities.productdetail.models.PlanDetailData
import com.example.connectus.activities.productdetail.models.PlanDetailItemListData
import com.example.connectus.activities.productdetail.models.ProductDetailData
import com.example.connectus.activities.productdetail.models.ProductImageData
import com.example.connectus.activities.productdetail.models.VendorProfileData
import com.example.connectus.activities.testimonilist.model.TestimonyData

class ProductDetailViewModel : ViewModel() {
    private val _data = MutableLiveData<ProductDetailData>()
    val data: LiveData<ProductDetailData> get() = _data

    init {
        val images: List<ProductImageData> = listOf(
            ProductImageData("https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc="),
            ProductImageData("https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc="),
            ProductImageData("https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc="),
            ProductImageData("https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc="),
        )

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

        val pricePlan: List<PlanDetailData> = listOf(
            PlanDetailData(
                1,
                "Silver",
                28920000,
                "Silver plan",
                planItemList
            ),
            PlanDetailData(
                2,
                "Gold",
                34500000,
                "Gold plan",
                planItemList
            ),
            PlanDetailData(
                3,
                "Premium",
                45600000,
                "Premium plan",
                planItemList
            )
        )

        val testimony: List<TestimonyData> = listOf(
            TestimonyData(
                "https://images.unsplash.com/photo-1587691592099-24045742c181?q=80&w=3008&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "2023-11-30T12:30:00",
                4,
                "Antoni",
                "Mereka mewujudkan acara product launching kami menjadi pengalaman yang tak terlupakan. Perhatian mereka terhadap detail, ide-ide kreatif, dan dedikasi mereka terhadap visi kami sangat luar biasa."
            ),
            TestimonyData(
                "https://images.unsplash.com/photo-1587691592099-24045742c181?q=80&w=3008&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "2023-11-31T12:30:00",
                5,
                "Antoni Luthfi",
                "Mereka mewujudkan acara product launching kami menjadi pengalaman yang tak terlupakan. Perhatian mereka terhadap detail, ide-ide kreatif, dan dedikasi mereka terhadap visi kami sangat luar biasa."
            ),
        )

        val mockData = ProductDetailData(
            1,
            images,
            "Product Launching for Company",
            "4.8",
            vendorProfile,
            "Welcome to our extraordinary product launch event! We invite you to unveil your latest innovation with style and impact. Our team specializes in creating memorable product launches that captivate your audience and leave a lasting impression. From concept to execution, we help your product shines in the spotlight.",
            pricePlan,
            testimony
        )

        _data.value = mockData
    }
}