package com.ngurah.githubuser.repository

import com.ngurah.githubuser.datastore.SettingPreference
import kotlinx.coroutines.flow.Flow

class SettingRepo private constructor(private val settingPreference: SettingPreference){
    fun getThemeSetting(): Flow<Boolean> = settingPreference.getThemeSetting()

    suspend fun saveThemeSetting(isDarkModeActive: Boolean){
        settingPreference.saveThemeSetting(isDarkModeActive)
    }

    companion object{
        @Volatile
        private var instance: SettingRepo? = null
        fun getInstance(settingPreference: SettingPreference): SettingRepo =
            instance?: synchronized(this){
                instance?: SettingRepo(settingPreference)
            }.also { instance = it }
    }
}