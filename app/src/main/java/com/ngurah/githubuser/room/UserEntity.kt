package com.ngurah.githubuser.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserEntity(
    @field:ColumnInfo(name = "username")
    @field: PrimaryKey
    val username: String,

    @field:ColumnInfo(name = "avatar_url")
    val avatarUrl:String?,

    @field:ColumnInfo(name = "name")
    val name: String,
)