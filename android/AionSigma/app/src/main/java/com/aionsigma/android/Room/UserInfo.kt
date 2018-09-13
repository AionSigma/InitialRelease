package com.aionsigma.android.Room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

import java.sql.Date
import java.util.UUID

@Entity(tableName = "userInfos")
data class UserInfo
/*public UserInfo(){
        userInfoId = UUID.randomUUID().toString();
        this.createdAt = new Date(System.currentTimeMillis()).getTime();
        this.synced = -1;
    }*/
(@field:ColumnInfo(name = "data")
 var data: String?, @field:ColumnInfo(name = "phoneNumber")
 var phoneNumber: String?, @field:ColumnInfo(name = "type")
 var type: String?) {

    @PrimaryKey
    @ColumnInfo(name = "userInfoId")
    var userInfoId: String = UUID.randomUUID().toString()

    @ColumnInfo(name = "createdAt")
    var createdAt: Long = 0

    @ColumnInfo(name = "synced")
    var synced: Long = 0

    init {
        this.createdAt = Date(System.currentTimeMillis()).time
        this.synced = -1
    }
}
