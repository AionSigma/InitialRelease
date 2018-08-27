package com.aionsigma_app.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserInfoDao {
    @Query("SELECT * FROM userinfos")
    List<UserInfo> getAll();

    @Query("SELECT * FROM userinfos WHERE userInfoId IN (:userInfoIds)")
    List<UserInfo> loadAllByIds(String[] userInfoIds);

    @Query("UPDATE userinfos SET synced = :now WHERE userInfoId IN (:userInfoIds)")
    void updateSynced(String[] userInfoIds, long now);

    @Query("SELECT * FROM userinfos WHERE synced > 0 Order by createdAt asc LIMIT :limit")
    List<UserInfo> getAllForSync(int limit);

    @Insert
    void insert(UserInfo... userInfo);

    @Query("Delete From userInfos Where synced is not null")
    void delete();

}
