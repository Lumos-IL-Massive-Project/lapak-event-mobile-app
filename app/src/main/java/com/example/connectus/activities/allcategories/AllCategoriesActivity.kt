package com.example.connectus.activities.allcategories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.connectus.activities.allcategories.adapter.RecyclerViewCategoryAdapter
import com.example.connectus.activities.allcategories.model.CategoryData
import com.example.connectus.databinding.ActivityAllCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCategoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTopBar()
        initCategoryList()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Kategori"
    }

    private fun initCategoryList() {
        val categoryList: List<CategoryData> = listOf(
            CategoryData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Pernikahan"
            ),
            CategoryData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Olahraga"
            ),
            CategoryData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Ulang Tahun"
            ),
            CategoryData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Bazar"
            ),
            CategoryData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Konser Musik"
            ),
            CategoryData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Seminar"
            ),
            CategoryData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Gathering"
            )
        )

        binding.rvCategories.adapter = RecyclerViewCategoryAdapter(this, categoryList)
        binding.rvCategories.layoutManager = GridLayoutManager(this, 2)
    }
}