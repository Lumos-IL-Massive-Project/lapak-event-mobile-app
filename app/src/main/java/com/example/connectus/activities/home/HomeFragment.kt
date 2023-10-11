package com.example.connectus.activities.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.connectus.R
import com.example.connectus.activities.home.adapters.GridViewMenuAdapter
import com.example.connectus.activities.home.adapters.RecyclerViewPopularProductAdapter
import com.example.connectus.activities.home.adapters.ViewPagerCarouselAdapter
import com.example.connectus.activities.home.models.CarouselData
import com.example.connectus.activities.home.models.MenuData
import com.example.connectus.activities.home.models.ProductData
import com.example.connectus.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val carouselDataList = ArrayList<CarouselData>()
    private lateinit var dotsIndicator: ArrayList<TextView>

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initCarousel()
        initMenu()
        initSearchInput()
        initPopularProduct()
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        initCarousel()
//        initMenu()
//        initSearchInput()
//        initPopularProduct()
//    }
    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    private fun initCarousel() {
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            var index = 0
            override fun run() {
                if (index == carouselDataList.size) index = 0
                Log.e("Runnable", "$index")
                binding.viewPager2.currentItem = index
                index++
                handler.postDelayed(this, 2000)
            }
        }

        carouselDataList.add(
            CarouselData("https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=")
        )
        carouselDataList.add(
            CarouselData("https://images.unsplash.com/photo-1624555130581-1d9cca783bc0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2071&q=80")
        )
        carouselDataList.add(
            CarouselData("https://images.unsplash.com/photo-1617854818583-09e7f077a156?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80")
        )

        val adapter = ViewPagerCarouselAdapter(carouselDataList)
        binding.viewPager2.adapter = adapter
        dotsIndicator = ArrayList()
        setIndicator()

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })
    }

    private fun initMenu() {
        val gridView = binding.gvMenu
        val items = listOf(
            MenuData("Pernikahan", R.drawable.ic_wedding),
            MenuData("Olahraga", R.drawable.ic_sport),
            MenuData("Ulang Tahun", R.drawable.ic_birthday),
            MenuData("Bazar", R.drawable.ic_bazaar),
            MenuData("Konser Musik", R.drawable.ic_music_concert),
            MenuData("Seminar", R.drawable.ic_seminar),
            MenuData("Gathering", R.drawable.ic_gathering),
            MenuData("Menu Lainnya", R.drawable.ic_other_menu),
        )

        val adapter = GridViewMenuAdapter(requireContext(), items)
        gridView.adapter = adapter
    }

    private fun initSearchInput() {
        binding.etSearchFixed.setOnClickListener(this)
    }

    private fun initPopularProduct() {
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

        binding.rvPopularProduct.adapter = RecyclerViewPopularProductAdapter(requireContext(), productDataList)
        binding.rvPopularProduct.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    fun handleMenu(v: View?) {
        Toast.makeText(requireContext(), "Tombol menu diklik", Toast.LENGTH_SHORT).show()
    }

    private fun selectedDot(position: Int) {
        for (i in 0 until carouselDataList.size) {
            if (i == position) {
                dotsIndicator[i].setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_700))
            } else {
                dotsIndicator[i].setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_200))
            }
        }
    }

    private fun setIndicator() {
        for (i in 0 until carouselDataList.size) {
            dotsIndicator.add(TextView(requireContext()))
            dotsIndicator[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            dotsIndicator[i].textSize = 12f

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(8, 0, 8, 0)

            dotsIndicator[i].layoutParams = layoutParams
            binding.dotsIndicator.addView(dotsIndicator[i])
        }
    }
}