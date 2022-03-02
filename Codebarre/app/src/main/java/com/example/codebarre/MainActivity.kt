package com.example.codebarre

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import org.json.JSONObject
import org.w3c.dom.Text


class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button_scan)
        button.setOnClickListener(this)
    }
    @SuppressLint("SetTextI18n")
    private val barcodeLauncher = registerForActivityResult(ScanContract()) {
            result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                this@MainActivity,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
            showResult(result.contents)
        }
    }

    private fun showResult(isbn: String) {
        var gt =  GetData()
        val jsonisbn = JSONObject(gt.execute("https://www.googleapis.com/books/v1/volumes?q=isbn:$isbn").get())

        findViewById<TextView>(R.id.TextView_Title).text = jsonisbn.getJSONArray("items").getJSONObject(1).getJSONObject("volumeInfo").getString("title")

        findViewById<TextView>(R.id.TextViewAuteur).text = jsonisbn.getJSONArray("items").getJSONObject(1).getJSONObject("volumeInfo").getJSONArray("authors").getString(0)

        findViewById<TextView>(R.id.textViewEditeur).text = jsonisbn.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("publisher")

        findViewById<TextView>(R.id.TextViewYear).text =  jsonisbn.getJSONArray("items").getJSONObject(1).getJSONObject("volumeInfo").getString("publishedDate")

        findViewById<TextView>(R.id.TextViewLangue).text = jsonisbn.getJSONArray("items").getJSONObject(1).getJSONObject("volumeInfo").getString("language")

        findViewById<TextView>(R.id.TextViewCollection).text = jsonisbn.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("publisher")

        findViewById<TextView>(R.id.TextViewASIN).text = jsonisbn.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("industryIdentifiers").getJSONObject(0).getString("identifier")

        val img = findViewById<ImageView>(R.id.imageView)
        var image_url = jsonisbn.getJSONArray("items").getJSONObject(1).getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail").split("://")
        val http = image_url[0]  + "s"
        val image_url_total = image_url[0]  + "s://" + image_url[1]
        val image = GetData2();
        img.setImageBitmap(image.execute(image_url_total).get())
    }

    // Launch
    override fun onClick(view: View?) {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
        options.setPrompt("Scan a barcode")
        options.setCameraId(0) // Use a specific camera of the device
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        options.setOrientationLocked(false)
        barcodeLauncher.launch(options)

    }
}