package com.ngurah.githubuser.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userEntity: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT * FROM user ORDER BY username ASC")
    fun getFavoriteUser(): LiveData<List<UserEntity>>

    @Query("SELECT EXISTS(SELECT * FROM user WHERE username = :username)")
    fun isFavoriteUser(username: String): LiveData<Boolean>

}