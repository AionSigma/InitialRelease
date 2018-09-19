package com.aionsigma.android.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.aionsigma.android.Model.MenuItem
import com.aionsigma.android.R

class LeftMenuAdapter(context: Context, menuItems:List<MenuItem>): BaseAdapter() {

    val context = context
    val menuItems = menuItems

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val menuView : View = LayoutInflater.from(context).inflate(R.layout.left_menu_item,null)
        val menuTitle : TextView = menuView.findViewById(R.id.tvTitle)
        val menuBadge : TextView = menuView.findViewById(R.id.tvbadgeInfo)

        val menu = menuItems[position]

        menuTitle.text = menu.title
        menuBadge.text = menu.badgeInfo

        if(menu.badgeInfo.isNullOrEmpty()){
            menuBadge.visibility = View.INVISIBLE
        }

        return menuView
    }

    override fun getItem(position: Int): Any {
        return menuItems[position]
    }

    override fun getItemId(position: Int): Long {
        return  0
    }

    override fun getCount(): Int {
        return menuItems.count()
    }
}