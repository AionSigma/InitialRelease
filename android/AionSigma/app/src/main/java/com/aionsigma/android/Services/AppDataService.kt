package com.aionsigma.android.Services

import com.aionsigma.android.Constants.ConstMenu
import com.aionsigma.android.Model.MenuItem


object AppDataService {
    val menuLeftItems = listOf(
            MenuItem(ConstMenu.MY_PROFILE,"My Profile"),
            MenuItem(ConstMenu.MY_CIRCLE,"My Circle"),
            MenuItem(ConstMenu.DEPOSITS,"Deposits"),
            MenuItem(ConstMenu.FRIENDS,"Friends",null, "5"),
            MenuItem(ConstMenu.INVITATIONS,"Invitations"),
            MenuItem(ConstMenu.JOIN,"Join"),
            MenuItem(ConstMenu.SETTING,"Setting"),
            MenuItem(ConstMenu.LOGOUT,"Logout")
    )
}