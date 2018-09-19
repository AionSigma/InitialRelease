package com.aionsigma.android.Services

import com.aionsigma.android.Model.MenuItem


object AppDataService {
    val menuLeftItems = listOf(
            MenuItem("My Circle"),
            MenuItem("Deposits"),
            MenuItem("Friends",null, "5"),
            MenuItem("Invitations"),
            MenuItem("Join"),
            MenuItem("Setting"),
            MenuItem("Logout")
    )
}