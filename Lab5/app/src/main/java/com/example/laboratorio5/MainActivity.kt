package com.example.laboratorio5

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.laboratorio5.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import java.io.InputStreamReader
import android.app.DownloadManager.Request
import android.util.Log


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        abrirDivisas().start()
    }
    private fun abrirDivisas():Thread{
        return Thread {
            val url= URL("https://v6.exchangerate-api.com/v6/e924993767594b360a574afa/latest/USD")
            val coneccion = url.openConnection() as HttpsURLConnection
            if(coneccion.responseCode==200){
                val inputSystem = coneccion.inputStream
                val inputStreamReader = InputStreamReader(inputSystem,"UTF-8")
                val Llamare = Gson().fromJson(inputStreamReader,llamare::class.java)
                modificarUI(Llamare)
                inputStreamReader.close()
                inputSystem.close()
            }
            else{
                binding.divisaBase.text="No se obtuvieron datos"
            }
        }
    }

    private fun modificarUI(Llamare: llamare) {
        runOnUiThread {
            kotlin.run {
                binding.ultimaUpdate.text = Llamare.time_last_update_utc
                binding.quetzales.text=String.format("GTQ: %.2f ",Llamare.rates.GTQ)
                binding.euro.text=String.format("EUR %.2f",Llamare.rates.EUR)
            }
        }
    }
}