package com.alangeorge.android.kotlin.bookplay.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.alangeorge.android.kotlin.bookplay.R
import com.alangeorge.android.kotlin.bookplay.domain.commands.RequestForecastCommand
import com.alangeorge.android.kotlin.bookplay.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecast_list.layoutManager = LinearLayoutManager(this)

        async() {
            val forecasts = RequestForecastCommand(94110).execute()
            uiThread {
                forecast_list.adapter = ForecastListAdapter(forecasts, { f -> Log.d(javaClass.simpleName, "${f.date}") })
            }
        }
    }
}
