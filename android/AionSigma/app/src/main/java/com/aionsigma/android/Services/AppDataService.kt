package com.aionsigma.android.Services

import com.aionsigma.android.Constants.ConstMenu
import com.aionsigma.android.Model.MenuItem.MenuItemModel


object AppDataService {
    val menuLeftItems = listOf(
            MenuItemModel(ConstMenu.MY_PROFILE, "My Profile"),
            MenuItemModel(ConstMenu.MY_CIRCLE, "My Circle"),
            MenuItemModel(ConstMenu.DEPOSITS, "Deposits"),
            MenuItemModel(ConstMenu.FRIENDS, "Friends", null, "5"),
            MenuItemModel(ConstMenu.INVITATIONS, "Invitations"),
            MenuItemModel(ConstMenu.JOIN, "Join"),
            MenuItemModel(ConstMenu.SETTING, "Setting"),
            MenuItemModel(ConstMenu.LOGOUT, "Logout")
    )
}