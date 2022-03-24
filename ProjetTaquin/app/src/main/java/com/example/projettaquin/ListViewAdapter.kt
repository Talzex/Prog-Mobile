package com.example.projettaquin

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(c: Context, listScore: ArrayList<String>) : BaseAdapter() {
    val mContext = c
    val mscoreList = listScore
    override fun getCount(): Int {
        return mscoreList.size
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val textView : TextView
        if(p1 == null){
            textView = TextView(mContext)
        } else {
            textView = p1 as TextView
        }

        textView.text = mscoreList[p0]

        return textView
    }
}