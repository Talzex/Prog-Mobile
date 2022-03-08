package com.example.projettaquin

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView

class ImageAdapter(c: Context, imgIDs: Array<Int>) : BaseAdapter() {
    private val mContext: Context = c
    private val imagesID : Array<Int> = imgIDs

    override fun getCount(): Int {
        return imagesID.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val mImageView: ImageView
        if (p1 == null) {
            mImageView = ImageView(mContext)
            mImageView.layoutParams = AbsListView.LayoutParams(230, 230)
            mImageView.scaleType = ImageView.ScaleType.CENTER_CROP
            mImageView.setPadding(16, 16, 16, 16)
        } else {
            mImageView = p1 as ImageView
        }
        mImageView.setImageResource(imagesID[p0])
        return mImageView
    }

}