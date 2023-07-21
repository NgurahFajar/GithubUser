package com.ngurah.githubuser.injection

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.ngurah.githubuser.api.ApiConfig
import com.ngurah.githubuser.datastore.SettingPreference
import com.ngurah.githubuser.repository.SettingRepo
import com.ngurah.githubuser.repository.UserRepo
import com.ngurah.githubuser.room.UserDatabase

object Injection{
    fun provideUserRepository(context: Context): UserRepo {
        val apiService = ApiConfig.getApiService()
        val database = UserDatabase.getInstance(context)
        val dao = database.userDao()
        return UserRepo.getInstance(apiService, dao)
    }

    fun provideSettingsRepository(dataStore: DataStore<Preferences>): SettingRepo {
        val settingPreferences = SettingPreference.getInstance(dataStore)
        return SettingRepo.getInstance(settingPreferences)
    }
}