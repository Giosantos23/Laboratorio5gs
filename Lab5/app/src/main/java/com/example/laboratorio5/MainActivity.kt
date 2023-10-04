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

// hilo
private val DownloadManager.Request.EUR: Any?
    get() {
        TODO()
    }
private val Request.GTQ: Any?
    get() {
        TODO("Not yet implemented")
    }
private val Request.time_last_update_utc: CharSequence?
    get() {
        TODO("Not yet implemented")
    }

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun abrirDivisas():Thread{
        return Thread{
            val url=URL("https://v6.exchangerate-api.com/v6/e924993767594b360a574afa/latest/USD")
            val coneccion = url.openConnection() as HttpsURLConnection
            if(coneccion.responseCode==200){
                val inputSistema = coneccion.inputStream
                val inputStreamReader = InputStreamReader(inputSistema,"UTF-8")
                val llamare = Gson().fromJson(inputStreamReader,Request::class.java)
                modificarUI(llamare)
                inputStreamReader.close()
                inputSistema.close()
            }
            else{
                binding.divisaBase.text="No se obtuvieron datos"
            }
        }
    }

    private fun modificarUI(llamare: Request) {
        runOnUiThread {
            kotlin.run {
                binding.ultimaUpdate.text = llamare.time_last_update_utc
                binding.quetzales.text=String.format("GTQ: %.2f ",llamare.GTQ)
                binding.euro.text=String.format("EUR %.2f",llamare.EUR)
            }
        }
    }
}