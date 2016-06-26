package com.alangeorge.android.kotlin.bookplay.domain.commands

import com.alangeorge.android.kotlin.bookplay.data.ForecastRequest
import com.alangeorge.android.kotlin.bookplay.domain.mappers.ForecastDataMapper
import com.alangeorge.android.kotlin.bookplay.domain.model.ForecastList

class RequestForecastCommand(val zip: Long): Command<ForecastList> {
    override fun execute(): ForecastList =
        ForecastDataMapper().convertFromDataModel(zip, ForecastRequest(zip).execute())
}