package com.alangeorge.android.kotlin.bookplay.data.db

import com.alangeorge.android.kotlin.bookplay.domain.model.ForecastList
import com.alangeorge.android.kotlin.bookplay.extensions.clear
import com.alangeorge.android.kotlin.bookplay.extensions.parseList
import com.alangeorge.android.kotlin.bookplay.extensions.parseOpt
import com.alangeorge.android.kotlin.bookplay.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()) {

    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {

        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { it ->  DayForecast(HashMap(it))}

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { it -> CityForecast(HashMap(it), dailyForecast) }

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {

        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            forecasts.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }
}