package com.alangeorge.android.kotlin.bookplay.domain.mappers

import com.alangeorge.android.kotlin.bookplay.data.ForecastResult
import com.alangeorge.android.kotlin.bookplay.data.Forecast as ExternalForecast
import com.alangeorge.android.kotlin.bookplay.domain.model.Forecast
import com.alangeorge.android.kotlin.bookplay.domain.model.ForecastList
import java.text.DateFormat
import java.util.*

class ForecastDataMapper {
    fun convertFromDataModel(externalModel: ForecastResult): ForecastList =
        ForecastList(externalModel.city.name, externalModel.city.country, convertForecastsModel(externalModel.list));

    private fun convertForecastsModel(externalForecasts: List<ExternalForecast>): List<Forecast> {
        return externalForecasts.map { it ->
            Forecast(
                    convertDate(it.dt),
                    it.weather[0].description,
                    it.temp.max.toInt(),
                    it.temp.min.toInt(),
                    generateIconUrl(it.weather[0].icon)
            )
        }
    }

    private fun convertDate(date: Long): String =
        DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault()).format(date * 1000)

    private fun generateIconUrl(iconCode: String): String
            = "http://openweathermap.org/img/w/$iconCode.png"
}