package com.example.laboratorio6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.laboratorio6.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        TODO("Not yet implemented")
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.teleport.org/api/urban_areas/\n")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun buscarNombre(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val llamar=getRetrofit().create(servicioApi::class.java).getUrbanAreas("$query/imagenes")
            val city =llamar.body()
            if(llamar.isSuccessful){
                //
            }
            else{
                //
            }
        }
    }
}

