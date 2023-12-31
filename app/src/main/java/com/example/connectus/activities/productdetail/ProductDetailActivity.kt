package com.example.connectus.activities.productdetail

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.connectus.R
import com.example.connectus.activities.chatdetail.ChatDetailActivity
import com.example.connectus.activities.eoprofile.EventOrganizerProfileActivity
import com.example.connectus.activities.home.models.ProductData
import com.example.connectus.activities.productdetail.adapters.RecyclerViewPricePlanSelectorAdapter
import com.example.connectus.activities.productdetail.adapters.RecyclerViewProductAdapter
import com.example.connectus.activities.productdetail.adapters.RecyclerViewTestimonyAdapter
import com.example.connectus.activities.productdetail.adapters.ViewPagerProductImagesAdapter
import com.example.connectus.activities.productdetail.fragments.OrderBottomSheetModalFragment
import com.example.connectus.activities.productdetail.fragments.PricePlanFragment
import com.example.connectus.activities.productdetail.models.PlanDetailData
import com.example.connectus.activities.productdetail.models.PricePlanClickListener
import com.example.connectus.activities.productdetail.models.ProductDetailData
import com.example.connectus.activities.productdetail.models.ProductImageData
import com.example.connectus.activities.productdetail.models.VendorProfileData
import com.example.connectus.activities.productdetail.viewmodels.ProductDetailViewModel
import com.example.connectus.activities.testimonilist.TestimonyListActivity
import com.example.connectus.activities.testimonilist.model.TestimonyData
import com.example.connectus.databinding.ActivityProductDetailBinding
import com.example.connectus.utils.Constants.VENDOR_ID
import com.example.connectus.utils.RecyclerViewRowGapItemDecoration
import com.example.connectus.utils.startDynamicActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity(), PricePlanClickListener {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var viewModel: ProductDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        init()
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }

    override fun onPricePlanClicked(data: PlanDetailData) {
        supportFragmentManager.beginTransaction()
            .replace(
                binding.pricePlanFragmentContainer.id,
                PricePlanFragment(data)
            )
            .addToBackStack(null)
            .commit()
    }

    private fun init() {
        initTopBar()

        viewModel = ViewModelProvider(this)[ProductDetailViewModel::class.java]
        viewModel.data.observe(this, Observer { data ->
            initImageSlider(data.images)
            initVendorProfile(data.profile)
            initPricePlan(data.plans)
            initDescription(data.description)
            initTestimony(data.testimony)
            initButtonAction(data)
        })

        initVendorProduct()
        initRecommendationProduct()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Detail Produk"
    }

    private fun initImageSlider(images: List<ProductImageData>) {
        val adapter = ViewPagerProductImagesAdapter(images)
        binding.viewPager2.adapter = adapter

        binding.viewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.imageSliderIndicatorChip.text = "${position + 1} / ${images.size}"
                super.onPageSelected(position)
            }
        })
    }

    private fun initVendorProfile(profile: VendorProfileData) {
        binding.eventOrganizerProfileContainer.setOnClickListener {
            startDynamicActivity(
                this, EventOrganizerProfileActivity::class.java, data = arrayOf(
                    Pair(VENDOR_ID, profile.id.toString())
                )
            )
        }
    }

    private fun initPricePlan(plans: List<PlanDetailData>) {
        binding.rvPricePlanSelectors.adapter =
            RecyclerViewPricePlanSelectorAdapter(this, plans, this)
        binding.rvPricePlanSelectors.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rvPricePlanSelectors.addItemDecoration(RecyclerViewRowGapItemDecoration(8))

        this.onPricePlanClicked(plans[0])
    }

    private fun initDescription(description: String) {
        binding.tvDescription.text = description
    }

    private fun initTestimony(testimonyList: List<TestimonyData>) {
        binding.rvTestimonyList.adapter = RecyclerViewTestimonyAdapter(this, testimonyList)
        binding.rvTestimonyList.layoutManager = LinearLayoutManager(this)

        binding.btnSeeMoreTestimony.setOnClickListener {
            startDynamicActivity(this, TestimonyListActivity::class.java)
        }
    }

    private fun initVendorProduct() {
        val productDataList: List<ProductData> = listOf(
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
        )

        binding.rvVendorProduct.adapter = RecyclerViewProductAdapter(this, productDataList)
        binding.rvVendorProduct.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initRecommendationProduct() {
        val productDataList: List<ProductData> = listOf(
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
        )

        binding.rvRecommendationProduct.adapter = RecyclerViewProductAdapter(this, productDataList)
        binding.rvRecommendationProduct.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initButtonAction(data: ProductDetailData) {
        binding.btnChat.setOnClickListener {
            startDynamicActivity(
                this, ChatDetailActivity::class.java, data = arrayOf(
                    Pair("KEY_HEADER_TITLE", data.profile.name)
                )
            )
        }

        binding.btnOrderNow.setOnClickListener {
            val bottomSheetFragment = OrderBottomSheetModalFragment(data.plans)
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }
}