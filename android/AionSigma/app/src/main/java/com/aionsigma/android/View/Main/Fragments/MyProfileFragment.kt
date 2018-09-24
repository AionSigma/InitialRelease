package com.aionsigma.android.View.Main.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.aionsigma.android.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MyProfileFragment : Fragment() {

    val TAG : String = "MyProfileFragment"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_my_profile, container, false)

        val myProfile_btnAdd = view.findViewById<at.markushi.ui.CircleButton>(R.id.myProfile_btnAdd)
//        myProfile_btnAdd.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View) {
//                Log.d(TAG,"myProfile_btnAdd.setOnClickListener")
//            }
//        })
        myProfile_btnAdd.setOnClickListener{view->
            Log.d(TAG,"myProfile_btnAdd.setOnClickListener")
            Log.d(TAG,"myProfile_btnAdd.setOnClickListener1")
        }
        return view
    }




}

