package com.parsadehghan.data.mapper

import com.parsadehghan.domain.entity.WeatherState
import org.junit.Assert.*

import org.junit.Test

class WeatherIconMapperKtTest {

    @Test
    fun mapWeatherIcon() {
        assertEquals(mapWeatherIcon("01"),WeatherState.ClearSky)
    }
}