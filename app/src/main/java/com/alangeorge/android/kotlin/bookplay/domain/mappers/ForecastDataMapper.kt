package com.alangeorge.android.kotlin.bookplay.domain.mappers

import com.alangeorge.android.kotlin.bookplay.data.ForecastResult
import com.alangeorge.android.kotlin.bookplay.domain.model.Forecast
import com.alangeorge.android.kotlin.bookplay.domain.model.ForecastList
import com.alangeorge.android.kotlin.bookplay.data.Forecast as ExternalForecast

class ForecastDataMapper {
    fun convertFromDataModel(zipCode: Long, externalModel: ForecastResult): ForecastList =
        ForecastList(zipCode, externalModel.city.name, externalModel.city.country, convertForecastsModel(externalModel.list));

    private fun convertForecastsModel(externalForecasts: List<ExternalForecast>): List<Forecast> {
        return externalForecasts.map { it ->
            Forecast(
                    it.dt * 1000,
                    it.weather[0].description,
                    it.temp.max.toInt(),
                    it.temp.min.toInt(),
                    generateIconUrl(it.weather[0].icon)
            )
        }
    }

    private fun generateIconUrl(iconCode: String): String
            = "http://openweathermap.org/img/w/$iconCode.png"
}