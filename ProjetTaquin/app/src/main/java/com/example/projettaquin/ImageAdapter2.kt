package com.example.projettaquin

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView


class ImageAdapter2(c : Context, tilesArrayList: ArrayList<Tile>, level : Int) : BaseAdapter() {
    val tilesArrayList = tilesArrayList
    private val mContext = c
    private val numberbout = level
    private var emptytile = tilesArrayList[tilesArrayList.size-1]

    override fun getCount(): Int {
        return tilesArrayList.size;
    }

    override fun getItem(p0: Int): Any {
        return tilesArrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

     fun changePosition(position : Int) : Boolean{
         var change = false
         val tile = tilesArrayList[position]

        if(changePositionIsPossible(tile)){
            val tileposition = tile.posX * numberbout + tile.posY
            val emptytileposition = emptytile.posX * numberbout + emptytile.posY

            tile.swapPosition(emptytile)

            tilesArrayList.removeAt(tileposition)
            tilesArrayList.add(tileposition,emptytile)
            tilesArrayList.removeAt(emptytileposition)
            tilesArrayList.add(emptytileposition,tile)
            change = true
            notifyDataSetChanged()
        }
         return change
    }

    private fun changePositionIsPossible(tile : Tile) : Boolean{
        return (tile.posX == emptytile.posX && (tile.posY + 1 == emptytile.posY || tile.posY - 1 == emptytile.posY)) ||
                (tile.posY == emptytile.posY && (tile.posX + 1 == emptytile.posX || tile.posX - 1 == emptytile.posX))
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val mImageView: ImageView
        if (p1 == null) {
            mImageView = ImageView(mContext)
            when(numberbout){
                3 -> {
                    mImageView.layoutParams = AbsListView.LayoutParams(340, 340)
                }
                4 -> {
                    mImageView.layoutParams = AbsListView.LayoutParams(245, 245)
                }
                5 -> {
                    mImageView.layoutParams = AbsListView.LayoutParams(175, 175)
                }
            }

            mImageView.scaleType = ImageView.ScaleType.CENTER_CROP
            mImageView.setPadding(0, 0, 0, 0)
        } else {
            mImageView = p1 as ImageView
        }
        mImageView.setImageBitmap(tilesArrayList[p0].image)
        mImageView.setBackgroundResource(R.drawable.border)
        return mImageView
    }

}