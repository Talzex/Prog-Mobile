package com.example.tacheasynchrone


import android.os.AsyncTask
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class GetData : AsyncTask<String, String, String>() {
     override fun doInBackground(vararg uri: String?): String? {
        val responseString = ""
        var url: URL? = null
        val response = StringBuilder()
        try {
            url = URL(uri[0])
            val httpconn: HttpURLConnection = url.openConnection() as HttpURLConnection
            if (httpconn.getResponseCode() === HttpURLConnection.HTTP_OK) {
                val input = BufferedReader(InputStreamReader(httpconn.getInputStream()), 8192)
                var strLine: String? = null
                while (input.readLine().also { strLine = it } != null) {
                    response.append(strLine)
                }
                input.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return response.toString()
    }

     override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
            //Do anything with response..//
    }
}