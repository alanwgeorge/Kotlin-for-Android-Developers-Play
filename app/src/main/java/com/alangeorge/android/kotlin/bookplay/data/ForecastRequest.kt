package com.alangeorge.android.kotlin.bookplay.data

import com.google.gson.Gson
import java.net.URL

class ForecastRequest(val zip: String) {
    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=imperial&cnt=16"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    fun execute(): ForecastResult {
        val json = URL(COMPLETE_URL + zip).readText()
        return Gson().fromJson(json, ForecastResult::class.java)
    }
}