package com.parsadehghan.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parsadehghan.inteactor.GetCurrentTimeState
import com.parsadehghan.inteactor.GetCurrentWeather
import com.parsadehghan.inteactor.GetMultipleDaysWeather
import com.parsadehghan.inteactor.GetMultipleTimesWeather
import com.parsadehghan.domain.entity.TimeState
import com.parsadehghan.domain.entity.Weather
import com.parsadehghan.domain.exceptions.ErrorModel
import com.parsadehghan.inteactor.base.UseCaseCallback
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val getCurrentWeather: GetCurrentWeather,
    private val getCurrentTimeState: GetCurrentTimeState,
    private val getMultipleDaysWeather: GetMultipleDaysWeather,
    private val getMultipleTimesWeather: GetMultipleTimesWeather
) :
    ViewModel() {

    private val _currentWeather by lazy { MutableLiveData<Weather>() }
    val currentWeather: LiveData<Weather> get() = _currentWeather

    private val _error by lazy { MutableLiveData<ErrorModel>() }
    val error: LiveData<ErrorModel> get() = _error

    private val _currentTimeState by lazy { MutableLiveData<TimeState>() }
    val currentTimeState: LiveData<TimeState> get() = _currentTimeState

    private val _multipleDaysWeather by lazy { MutableLiveData<List<Weather>>() }
    val multipleDaysWeather: LiveData<List<Weather>> get() = _multipleDaysWeather

    private val _multipleTimeWeather by lazy { MutableLiveData<List<Weather>>() }
    val multipleTimeWeather: LiveData<List<Weather>> get() = _multipleTimeWeather

    private val _currentCityId by lazy { MutableLiveData<Int>() }
    val currentCityId: LiveData<Int> get() = _currentCityId

    fun getCurrentWeather() {
        viewModelScope.launch {
            getCurrentWeather.call(currentCityId.value).collect {
                when(it){
                    is UseCaseCallback.Success ->{ _currentWeather.value = it.data}
                    is UseCaseCallback.Error ->{ _error.value = it.errorModel}

                }
            }


        }
    }

    fun getCurrentTimeState() {
        viewModelScope.launch {
            getCurrentTimeState.call().collect {
                when(it){
                    is UseCaseCallback.Success -> {_currentTimeState.value = it.data}
                    is UseCaseCallback.Error ->{ _error.value = it.errorModel}

                }
            }
        }
    }

    fun getMultipleDaysWeather() {
        viewModelScope.launch {
            getMultipleDaysWeather.call(com.parsadehghan.domain.entity.RequestMultipleWeather(currentCityId.value, 7)).collect {
                when(it){
                    is UseCaseCallback.Success -> {_multipleDaysWeather.value = it.data}
                    is UseCaseCallback.Error ->{ _error.value = it.errorModel}

                }
            }

        }
    }

    fun getMultipleTimesWeather() {
        viewModelScope.launch {
            getMultipleTimesWeather.call(
                com.parsadehghan.domain.entity.RequestMultipleWeather(currentCityId.value, 7)).collect {
                when(it){
                    is UseCaseCallback.Success -> {_multipleTimeWeather.value = it.data}
                    is UseCaseCallback.Error -> {_error.value = it.errorModel}

                }
                }

        }
    }

    fun setCurrentCityId(value: Int) {
        _currentCityId.value = value
    }

    fun setCurrentWeather(value: Weather) {
        _currentWeather.value = value
    }
}