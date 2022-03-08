package com.parsadehghan.data.local

import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val preferences: PreferencesHelper
) : LocalDataSource {

}