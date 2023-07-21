package com.ngurah.githubuser.repository


import androidx.lifecycle.LiveData
import com.ngurah.githubuser.api.ApiService
import com.ngurah.githubuser.room.UserDao
import com.ngurah.githubuser.room.UserEntity

class UserRepo private constructor(private val apiService: ApiService, private val userDao: UserDao): BaseRepo() {
    suspend fun getUserByUsername(query: String) = safeApiCall{
        apiService.getUser(query)
    }

    suspend fun getUserDetail(usernmae: String) = safeApiCall {
        apiService.getDetailUser(usernmae)
    }

    suspend fun getUserFollowers(usernmae: String) = safeApiCall{
        apiService.getFollowers(usernmae)
    }

    suspend fun getUserFollowing(username: String) = safeApiCall {
        apiService.getFollowing(username)
    }

    suspend fun addToFavoriteUser(user: UserEntity) = userDao.insert(user)
    suspend fun deleteFavoriteUser(user: UserEntity) = userDao.delete(user)
    suspend fun deleteAllFavorite() = userDao.deleteAll()

    fun getFavoriteUsers(): LiveData<List<UserEntity>> = userDao.getFavoriteUser()
    fun isFavoriteUser(usernmae: String): LiveData<Boolean> = userDao.isFavoriteUser(usernmae)

    companion object{
        @Volatile
        private var instance: UserRepo? = null
        fun getInstance(apiService: ApiService, userDao: UserDao): UserRepo =
            instance?: synchronized(this){
                instance?: UserRepo(apiService, userDao)
            }.also { instance = it }
    }
}