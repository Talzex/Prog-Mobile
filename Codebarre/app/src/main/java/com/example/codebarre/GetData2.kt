package com.example.codebarre


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class GetData2 : AsyncTask<String, Void, Bitmap>() {
    override fun doInBackground(vararg uri: String?): Bitmap? {
        var url: URL? = null
        var bmp : Bitmap? = null
        try {
            url = URL(uri[0])
            val httpconn: HttpURLConnection = url.openConnection() as HttpURLConnection
            if (httpconn.getResponseCode() === HttpURLConnection.HTTP_OK) {
                val `is`: InputStream = httpconn.getInputStream()
                bmp = BitmapFactory.decodeStream(`is`)
                if(bmp != null){
                    return bmp
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmp
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        //Do anything with response..//
    }
}