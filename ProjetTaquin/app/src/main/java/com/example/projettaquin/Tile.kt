package com.example.projettaquin

import android.graphics.Bitmap

class Tile(x : Int, y : Int, img : Bitmap?, num : Int) {
    var posX = x
    var posY = y
    var image = img
    var numero = num

    fun swapPosition(tile : Tile){
        val temp = Tile(tile.posX,tile.posY,tile.image,tile.numero)
        tile.posX = this.posX
        tile.posY = this.posY

        this.posX = temp.posX
        this.posY = temp.posY

    }
}