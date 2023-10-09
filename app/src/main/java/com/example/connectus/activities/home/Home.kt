package com.example.connectus.activities.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import androidx.viewpager2.widget.ViewPager2
import com.example.connectus.R
import com.example.connectus.activities.home.adapters.CarouselAdapter
import com.example.connectus.activities.home.fragments.Header
import com.example.connectus.activities.home.models.CarouselData
import com.example.connectus.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: CarouselAdapter
    private val carouselDataList = ArrayList<CarouselData>()
    private lateinit var dotsIndicator: ArrayList<TextView>

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            add(binding.homeHeader.id, Header())
            commit()
        }

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

        adapter = CarouselAdapter(carouselDataList)
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

    fun handleMenu(v: View?) {
        Toast.makeText(this, "Tombol menu diklik", Toast.LENGTH_SHORT).show()
    }

    private fun selectedDot(position: Int) {
        for (i in 0 until carouselDataList.size) {
            if (i == position) {
                dotsIndicator[i].setTextColor(ContextCompat.getColor(this, R.color.purple_700))
            } else {
                dotsIndicator[i].setTextColor(ContextCompat.getColor(this, R.color.purple_200))
            }
        }
    }

    private fun setIndicator() {
        for (i in 0 until carouselDataList.size) {
            dotsIndicator.add(TextView(this))
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

    override fun onStart() {
        super.onStart()
        handler.post(runnable)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }
}