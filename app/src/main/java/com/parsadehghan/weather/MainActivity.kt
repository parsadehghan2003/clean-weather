package com.parsadehghan.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.parsadehghan.domain.entity.TimeState
import com.parsadehghan.domain.entity.Weather
import com.parsadehghan.weather.adapter.DaysAdapter
import com.parsadehghan.weather.adapter.TimesAdapter
import com.parsadehghan.weather.mapper.getWeatherIcon
import com.parsadehghan.weather.components.SpanningLinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var toast: Toast? = null
    private lateinit var daysAdapter: DaysAdapter
    private lateinit var timesAdapter: TimesAdapter
    private lateinit var cityData: List<Pair<Int, String>>

    private val mainViewModel: MainViewModel by viewModels()
    private var currentTimeState: TimeState? = null
    private var isNotNowWeather = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        registerObservers()
        mainViewModel.getCurrentTimeState()

        val cityIds = resources.getIntArray(R.array.city_ids)
        val cityNames = resources.getStringArray(R.array.city_name)
        cityData = cityIds.zip(cityNames)
        mainViewModel.setCurrentCityId(cityData.first().first)
    }

    @SuppressLint("SetTextI18n")
    private fun registerObservers() {
        mainViewModel.currentCityId.observe(this, Observer {
            mainViewModel.getCurrentWeather()
            mainViewModel.getMultipleDaysWeather()
            mainViewModel.getMultipleTimesWeather()
            isNotNowWeather = false
        })

        mainViewModel.error.observe(this, Observer {
            toast?.cancel()
            toast = Toast.makeText(this, it.message, Toast.LENGTH_LONG)
            toast?.show()
        })

        mainViewModel.currentTimeState.observe(this, Observer { timeState ->
            setBackgroundContainer(timeState)
            currentTimeState = timeState
        })

        mainViewModel.currentWeather.observe(this, Observer {
            txtDegree.text = "${it.temp}°"
            if (it.min != it.max)
                txtDegreeRange.text = "${it.min}°/${it.max}°"
            else
                txtDegreeRange.text = ""
            setWeatherIcon(it)
        })

        mainViewModel.multipleDaysWeather.observe(this, Observer {
            daysAdapter.setData(it, currentTimeState!!)
        })

        mainViewModel.multipleTimeWeather.observe(this, Observer {
            timesAdapter.setData(it, currentTimeState!!)
        })
    }

    private fun setBackgroundContainer(timeState: TimeState?) {
        container.setBackgroundResource(
            when (timeState) {
                TimeState.Dawn ->
                    R.drawable.background_dawn
                TimeState.Night ->
                    R.drawable.background_night
                TimeState.Morning ->
                    R.drawable.background_morning
                TimeState.Evening ->
                    R.drawable.background_evening
                TimeState.Noon ->
                    R.drawable.background_noon
                else ->
                    R.drawable.background_noon
            }
        )
    }

    private fun setWeatherIcon(weather: Weather) {
        imgIcon.setImageResource(getWeatherIcon(weather.state, currentTimeState!!))
    }


    private fun initViews() {
        rvTimes.layoutManager = SpanningLinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        timesAdapter = TimesAdapter(this)
        rvTimes.adapter = timesAdapter

        rvDays.layoutManager = LinearLayoutManager(this)
        daysAdapter = DaysAdapter(this) { weather ->
            isNotNowWeather = true
            mainViewModel.setCurrentWeather(weather)
        }
        rvDays.adapter = daysAdapter

        spCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainViewModel.setCurrentCityId(cityData[position].first)
            }

        }
    }

    override fun onPause() {
        super.onPause()
        toast?.cancel()
    }

    override fun onBackPressed() {
        if (isNotNowWeather) {
            isNotNowWeather = false
            mainViewModel.setCurrentCityId(cityData[spCity.selectedItemPosition].first)
        } else
            super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }
}