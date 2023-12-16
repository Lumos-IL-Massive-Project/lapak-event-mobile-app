package com.example.connectus.activities.eoprofile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.connectus.databinding.FragmentEventOrganizerAboutBinding

class EventOrganizerAboutFragment : Fragment() {
    private var binding: FragmentEventOrganizerAboutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventOrganizerAboutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun init() {
        val htmlContent = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Selamat datang di Grand Galas EO</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        line-height: 1.6;
                        margin: 20px;
                    }
                    h1 {
                        color: #004080;
                    }
                    h2 {
                        color: #0080ff;
                    }
                    p {
                        text-align: justify;
                    }
                </style>
            </head>
            <body>

                <h1>Selamat datang di Grand Galas EO</h1>

                <p>Grand Galas EO, mitra terpercaya Anda dalam menghadirkan pengalaman acara B2B yang luar biasa. Kami adalah tim yang berdedikasi untuk menciptakan momen berharga dan peluang bisnis yang tak terlupakan bagi klien kami.</p>

                <h2>Misi Kami</h2>

                <p>Misi kami adalah menjadi penyelenggara acara B2B pilihan utama yang mengubah ide menjadi realitas. Kami memahami betapa pentingnya setiap acara dalam memperkuat koneksi bisnis, mempromosikan pertumbuhan, dan memperluas jaringan Anda. Itulah mengapa kami bekerja keras untuk menghasilkan pengalaman yang melebihi harapan Anda.</p>

                <h2>Visi Kami</h2>

                <p>Visi kami adalah menjadi pionir dalam industri event B2B dengan inovasi yang tak terbatas dan layanan yang berkualitas tinggi. Kami berkomitmen untuk terus memperluas pengetahuan dan keterampilan kami, sehingga dapat memenuhi kebutuhan unik setiap klien dengan sempurna.</p>

                <h2>Mengapa Memilih Kami?</h2>

                <p>Tim kami terdiri dari profesional berpengalaman yang telah sukses melaksanakan berbagai acara B2B dari skala kecil hingga besar. Kami memahami dinamika bisnis Anda dan tahu bagaimana membuat acara yang relevan dan berdampak tinggi. Kami tidak hanya menjalankan acara, kami menciptakannya. Kami selalu berpikir di luar kotak untuk memberikan pengalaman yang unik dan tak terlupakan bagi peserta Anda. Kesuksesan acara Anda adalah kesuksesan kami. Kami bekerja erat dengan Anda untuk memastikan setiap detail terpenuhi dan visi Anda diwujudkan. Dengan jaringan yang luas di dunia B2B, kami dapat membantu Anda terhubung dengan mitra potensial, pelanggan baru, dan pemangku kepentingan yang relevan.</p>

                <p>Hubungi kami hari ini untuk memulai perjalanan menuju sukses bersama Grand Galas EO.</p>

            </body>
            </html>
        """.trimIndent()
        binding?.webView?.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
    }
}
