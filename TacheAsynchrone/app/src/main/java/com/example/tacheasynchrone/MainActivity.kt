package com.example.tacheasynchrone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val city = findViewById<EditText>(R.id.EditTextCIty)
        val temperature = findViewById<TextView>(R.id.TextViewCelsius)
        val desc = findViewById<TextView>(R.id.textViewDesc)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val img = findViewById<ImageView>(R.id.imageView)
        var selectedcity : String = " "
        var gt = GetData()


        val apiKey = "802582867bed4393a7085053221702"

        try {
            button.setOnClickListener {
                if(!city.text.isEmpty()){
                    selectedcity = city.text.toString()

                } else  {
                    selectedcity = spinner.selectedItem.toString()

                }
                val meteourl =
                    "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=" + apiKey + "&q=" + selectedcity + "&num_of_days=2&tp=3&format=JSON"
                val jsonmeteo = JSONObject(gt.execute(meteourl).get())
                val temp_c = jsonmeteo.getJSONObject("data").getJSONArray("current_condition").getJSONObject(0).getInt("temp_C")
                val weather_desc = jsonmeteo.getJSONObject("data").getJSONArray("current_condition").getJSONObject(0).getJSONArray("weatherDesc").getJSONObject(0).getString("value")
                var img_url = jsonmeteo.getJSONObject("data").getJSONArray("current_condition").getJSONObject(0).getJSONArray("weatherIconUrl").getJSONObject(0).getString("value")

                temperature.text = "Température : $temp_c"
                desc.text = "Description : $weather_desc"
                val image = GetData2();
                img.setImageBitmap(image.execute(img_url).get())
                gt = GetData()
            }
        } catch (e : Exception){
            println(e.message)
        }


    }
}