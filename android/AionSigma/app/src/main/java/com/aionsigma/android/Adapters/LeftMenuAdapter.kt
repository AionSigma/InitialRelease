package com.aionsigma.android.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aionsigma.android.Model.MenuItem.MenuItemModel
import com.aionsigma.android.R

class LeftMenuAdapter(val context: Context, private val menuItemModels:List<MenuItemModel>, val itemClick: (MenuItemModel)-> Unit): RecyclerView.Adapter<LeftMenuAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.left_menu_item, parent, false)
        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return menuItemModels.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding(menuItemModels[position], context)
    }

    inner class Holder(itemView:View?, val itemClick: (MenuItemModel)-> Unit) : RecyclerView.ViewHolder(itemView){
        val title = itemView?.findViewById<TextView>(R.id.tvTitle)
        val badge = itemView?.findViewById<TextView>(R.id.tvbadgeInfo)

        fun binding(menu: MenuItemModel, context: Context){
            title?.text = menu.title
            badge?.text = menu.badgeInfo
            if(menu.badgeInfo.isNullOrEmpty()){
                badge?.visibility = View.INVISIBLE
            }

            itemView.setOnClickListener { itemClick(menu) }
        }
    }
}

//
//class LeftMenuAdapter(context: Context, menuItems:List<MenuItemModel>): BaseAdapter() {
//
//    val context = context
//    private val menuItems = menuItems
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val menuView : View
//        val holder : ViewHolder
//        if(convertView == null){
//            menuView= LayoutInflater.from(context).inflate(R.layout.left_menu_item,null)
//            holder = ViewHolder()
//            holder.title = menuView.findViewById(R.id.tvTitle)
//            holder.badge = menuView.findViewById(R.id.tvbadgeInfo)
//            menuView.tag = holder
//        }
//        else{
//            holder = convertView.tag as ViewHolder
//            menuView = convertView
//        }
//
//        val menu = menuItems[position]
//
//        holder.title?.text = menu.title
//        holder.badge?.text = menu.badgeInfo
//
//        if(menu.badgeInfo.isNullOrEmpty()){
//            holder.badge?.visibility = View.INVISIBLE
//        }
//
//        return menuView
//    }
//
//    override fun getItem(position: Int): Any {
//        return menuItems[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return  0
//    }
//
//    override fun getCount(): Int {
//        return menuItems.count()
//    }
//
//    private class ViewHolder{
//        var title : TextView? = null
//        var badge : TextView? = null
//    }
//}