package com.aionsigma.android.Room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserInfoDao {
    @Query("SELECT * FROM userinfos")
    fun getAll(): List<UserInfo>

    @Query("SELECT * FROM userinfos WHERE userInfoId IN (:userInfoIds)")
    fun loadAllByIds(userInfoIds: Array<String>): List<UserInfo>

    @Query("UPDATE userinfos SET synced = :now WHERE userInfoId IN (:userInfoIds)")
    fun updateSynced(userInfoIds: Array<String>, now: Long)

    @Query("SELECT * FROM userinfos WHERE synced > 0 Order by createdAt asc LIMIT :limit")
    fun getAllForSync(limit: Int): List<UserInfo>

    @Insert
    fun insert(vararg userInfo: UserInfo)

    @Query("Delete From userInfos Where synced is not null")
    fun delete()

}