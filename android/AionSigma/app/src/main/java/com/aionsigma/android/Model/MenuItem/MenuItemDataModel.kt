package com.aionsigma.android.Model.MenuItem

class MenuItemDataModel(val id : Int, val title: String, val icon: String? = null, val badgeInfo: String? = null){
    override fun toString(): String {
        return title
    }
}