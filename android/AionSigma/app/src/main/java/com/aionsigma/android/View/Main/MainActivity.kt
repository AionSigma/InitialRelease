package com.aionsigma.android.View.Main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.aionsigma.android.R
import kotlinx.android.synthetic.main.activity_main.*
import android.app.ActivityManager
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import com.aionsigma.android.Adapters.LeftMenuAdapter
import com.aionsigma.android.Services.AppDataService
import com.aionsigma.android.Services.SyncDataService
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.support.v7.widget.DividerItemDecoration
import android.util.Log
import com.aionsigma.android.Constants.ConstMenu
import com.aionsigma.android.Presenter.Account.AccountPresenter
import com.aionsigma.android.Ultilities.SharedPreferencesUtils
import com.aionsigma.android.View.Login.LoginActivity
import com.aionsigma.android.View.Main.Fragments.MyCircleFragment
import com.aionsigma.android.View.Main.Fragments.MyProfileFragment
import kotlinx.android.synthetic.main.nav_header_main.*


class MainActivity : AppCompatActivity() {

    lateinit var adapter: LeftMenuAdapter
    var actionBar: ActionBar? = null
    var fragmentManager : FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initial()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }


    @SuppressLint("ResourceType")
    private fun initial(): Unit{
        fragmentManager = supportFragmentManager
        actionBar = supportActionBar


        //Create First Fragment
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val myProfile = MyProfileFragment()
        fragmentTransaction?.add(R.id.frameLayout,myProfile)
        fragmentTransaction?.commit()
        actionBar?.title = AppDataService.menuLeftItems[0].title

        //Left menu init
        adapter = LeftMenuAdapter(this, AppDataService.menuLeftItems){ menuItem ->

            actionBar?.title = menuItem.title

            menuItemSelectedHandler(menuItem)
        }
        menu_left_list.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.recyclerview_divider)!!)

        menu_left_list.layoutManager = layoutManager
        menu_left_list.setHasFixedSize(true)
        menu_left_list.addItemDecoration(itemDecorator)

        //Services
        Log.d("Service", "Start")
        try {
            if (!isServiceRunning()) {
                val intent = Intent(this, SyncDataService::class.java)
                startService(intent)
                Log.d("Service", "Started")
            }
        } catch (ex: Exception) {
            Log.d("Service", ex.message)
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }

        //Drawer init
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        //Get UserInfo
        val userInfo = SharedPreferencesUtils.readUserLogin(this)
        if(userInfo!= null){
            main_tvName.text = userInfo.username
        }
        else{
            var loginIntent = Intent(this,LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

    @SuppressLint("ResourceType")
    private fun menuItemSelectedHandler(menuItemModel : com.aionsigma.android.Model.MenuItem.MenuItemDataModel){
        val fragmentTransaction = fragmentManager?.beginTransaction()
        when(menuItemModel.id){
            ConstMenu.MY_PROFILE ->{
                val myProfileFragment = MyProfileFragment()
                fragmentTransaction?.replace(R.id.frameLayout,myProfileFragment)
            }
            ConstMenu.MY_CIRCLE ->{
                val myCircle = MyCircleFragment()
                fragmentTransaction?.replace(R.id.frameLayout,myCircle)
            }
            ConstMenu.LOGOUT->{
                SharedPreferencesUtils.clearAll(this)
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                finish()
            }
        }
        fragmentTransaction?.commit()
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun isServiceRunning(): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        @Suppress("DEPRECATION")
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.aionsigma.android.Services.SyncDataService" == service.service.className) {
                return true
            }
        }
        return false
    }
}