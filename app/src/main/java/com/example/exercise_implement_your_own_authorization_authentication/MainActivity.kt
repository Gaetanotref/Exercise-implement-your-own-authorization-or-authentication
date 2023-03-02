package com.example.exercise_implement_your_own_authorization_authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.exercise_implement_your_own_authorization_authentication.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val logging = HttpLoggingInterceptor()
    private val authorizationInterceptor = AuthorizationInterceptor()
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authorizationInterceptor)
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://dog.ceo")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiServiceInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        logging.level = HttpLoggingInterceptor.Level.BODY

        retriveDetails()
    }
    private fun setDetails(dataResult : RetrofitData){
        binding.firstText.text = getString(R.string.primo_testo, dataResult.message)
    }
    private fun retriveDetails(){
        lifecycleScope.launch {
            try {
                val details = apiService.getData()
                setDetails(details)
            }catch (e:Exception){
                Log.d("MainActivity","ERROR : ${e.message}")
            }
        }
    }
}