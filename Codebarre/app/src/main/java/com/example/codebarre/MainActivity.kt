package com.example.codebarre

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import org.json.JSONObject


class MainActivity : AppCompatActivity(),View.OnClickListener,AdapterView.OnItemSelectedListener {
    var book_array = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button_scan)
        button.setOnClickListener(this)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter : ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, book_array)
        spinner.adapter = adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.onItemSelectedListener = this
    }
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

    @SuppressLint("SetTextI18n")
    private fun showResult(isbn: String) {
        var gt =  GetData()
        val jsonisbn = JSONObject(gt.execute("https://www.googleapis.com/books/v1/volumes?q=isbn:$isbn").get())

        findViewById<TextView>(R.id.TextView_Title).text = getString(R.string.text_title) + jsonisbn.getJSONArray("items").getJSONObject(1).getJSONObject("volumeInfo").getString("title")

        findViewById<TextView>(R.id.TextViewAuteur).text = getString(R.string.text_autor) + jsonisbn.getJSONArray("items").getJSONObject(1).getJSONObject("volumeInfo").getJSONArray("authors").getString(0)

        findViewById<TextView>(R.id.textViewEditeur).text = getString(R.string.text_editor) + jsonisbn.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("publisher")

        findViewById<TextView>(R.id.TextViewYear).text = getString(R.string.text_year) + jsonisbn.getJSONArray("items").getJSONObject(1).getJSONObject("volumeInfo").getString("publishedDate")

        findViewById<TextView>(R.id.TextViewLangue).text = getString(R.string.text_langue) + jsonisbn.getJSONArray("items").getJSONObject(1).getJSONObject("volumeInfo").getString("language")

        findViewById<TextView>(R.id.TextViewCollection).text = getString(R.string.text_collec) + jsonisbn.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("publisher")

        findViewById<TextView>(R.id.TextViewASIN).text = getString(R.string.text_asin) + jsonisbn.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("industryIdentifiers").getJSONObject(0).getString("identifier")

        val img = findViewById<ImageView>(R.id.imageView)
        var image_url = jsonisbn.getJSONArray("items").getJSONObject(1).getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail").split("://")
        val http = image_url[0]  + "s"
        val image_url_total = image_url[0]  + "s://" + image_url[1]
        val image = GetData2();
        img.setImageBitmap(image.execute(image_url_total).get())

        book_array.add(getString(R.string.text_title) + jsonisbn.getJSONArray("items").getJSONObject(1).getJSONObject("volumeInfo").getString("title"))
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

    override fun onItemSelected(arg0: AdapterView<*>?, arg1: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}