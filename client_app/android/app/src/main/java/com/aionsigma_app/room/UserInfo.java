package com.aionsigma_app.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.sql.Date;
import java.util.UUID;

@Entity(tableName = "userInfos")
public class UserInfo {
    @PrimaryKey @NonNull
    @ColumnInfo(name = "userInfoId")
    private String userInfoId;

    @ColumnInfo(name = "phoneNumber")
    private String phoneNumber;

    @ColumnInfo(name = "data")
    private String data;

    @ColumnInfo(name = "createdAt")
    private long createdAt;

    @Nullable
    @ColumnInfo(name = "synced")
    private long synced;

    @ColumnInfo(name = "type")
    private String type;


    /*public UserInfo(){
        userInfoId = UUID.randomUUID().toString();
        this.createdAt = new Date(System.currentTimeMillis()).getTime();
        this.synced = -1;
    }*/

    public UserInfo(String data, String phoneNumber, String type){
        userInfoId = UUID.randomUUID().toString();
        this.data = data;
        this.phoneNumber = phoneNumber;
        this.createdAt = new Date(System.currentTimeMillis()).getTime();
        this.synced = -1;
        this.type = type;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getSynced() {
        return synced;
    }

    public void setSynced(long synced) {
        this.synced = synced;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
// Getters and setters are ignored for brevity,
    // but they're required for Room to work.
}
