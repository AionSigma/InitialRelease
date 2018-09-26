package com.aionsigma.android.Services

import com.aionsigma.android.Constants.ConstMenu
import com.aionsigma.android.Model.MenuItem.MenuItemDataModel


object AppDataService {
    val menuLeftItems = listOf(
            MenuItemDataModel(ConstMenu.MY_PROFILE, "My Profile"),
            MenuItemDataModel(ConstMenu.MY_CIRCLE, "My Circle"),
            MenuItemDataModel(ConstMenu.DEPOSITS, "Deposits"),
            MenuItemDataModel(ConstMenu.FRIENDS, "Friends", null, "5"),
            MenuItemDataModel(ConstMenu.INVITATIONS, "Invitations"),
            MenuItemDataModel(ConstMenu.JOIN, "Join"),
            MenuItemDataModel(ConstMenu.SETTING, "Setting"),
            MenuItemDataModel(ConstMenu.LOGOUT, "Logout")
    )
}