package com.example.myapplication

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvCount: TextView
    private lateinit var RcVw: RecyclerView
    private lateinit var adapter: Adapter
    private var listQuakes = mutableListOf<Terremoto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTitle = findViewById(R.id.TxVw1)
        tvCount = findViewById(R.id.TxVw2)
        RcVw = findViewById(R.id.RcVw1)

        RcVw.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(listQuakes)
        RcVw.adapter = adapter

        getQuakes()

    }


    private fun getQuakes()
    {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ServiceApi::class.java).getQuakes("significant_month.geojson")
            val response = call.body()

            runOnUiThread{
                if (call.isSuccessful)
                {
                    val quakes = response?.features
                    quakes?.forEach {
                        listQuakes.add(it)
                    }

                    val metadata = response?.metadata
                    val title = metadata?.title
                    val count = metadata?.count

                    tvTitle.text = title
                    tvCount.text = "mostrando $count terremotos"

                    adapter.notifyDataSetChanged()
                }
            }

        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}