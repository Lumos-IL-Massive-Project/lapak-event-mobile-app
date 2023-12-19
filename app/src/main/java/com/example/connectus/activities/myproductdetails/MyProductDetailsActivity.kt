package com.example.connectus.activities.myproductdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.activities.myproductdetails.adapters.RecyclerViewPlanAdapter
import com.example.connectus.activities.myproductdetails.adapters.RecyclerViewUploadedImageAdapter
import com.example.connectus.activities.myproductdetails.fragments.ModalChooseCategoryFragment
import com.example.connectus.activities.myproductdetails.models.PlanData
import com.example.connectus.activities.myproductdetails.models.UploadedImageData
import com.example.connectus.databinding.ActivityMyProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initSelectCategoryButton()
        initUploadedImageList()
        initPlanList()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Tambah Produk"
    }

    private fun initSelectCategoryButton() {
        binding.btnSelectCategory.setOnClickListener {
            val modalChooseCategory = ModalChooseCategoryFragment()
            modalChooseCategory.show(supportFragmentManager, modalChooseCategory.tag)
        }
    }

    private fun initUploadedImageList() {
        val data = listOf(
            UploadedImageData(
                1,
                "test gambar.jpg",
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc="
            ),
            UploadedImageData(
                2,
                "test gambar.jpg",
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc="
            ),
            UploadedImageData(
                3,
                "test gambar.jpg",
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc="
            ),
            UploadedImageData(
                4,
                "test gambar.jpg",
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc="
            ),
        )

        binding.rvUploadedImages.adapter = RecyclerViewUploadedImageAdapter(data)
        binding.rvUploadedImages.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
    }

    private fun initPlanList() {
        val data = listOf(
            PlanData("Basic Plan"),
            PlanData("Silver Plan"),
        )

        binding.rvPlans.adapter = RecyclerViewPlanAdapter(data)
        binding.rvPlans.layoutManager = LinearLayoutManager(this)
    }
}