package com.alangeorge.android.kotlin.bookplay.domain.commands

import com.alangeorge.android.kotlin.bookplay.data.ForecastRequest
import com.alangeorge.android.kotlin.bookplay.domain.mappers.ForecastDataMapper
import com.alangeorge.android.kotlin.bookplay.domain.model.ForecastList

class RequestForecastCommand(val zip: String): Command<ForecastList> {
    override fun execute(): ForecastList =
        ForecastDataMapper().convertFromDataModel(ForecastRequest(zip).execute())
}