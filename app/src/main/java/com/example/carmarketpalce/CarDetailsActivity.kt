package com.example.carmarketpalce

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class CarDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_details)

        val name = intent.getStringExtra("CAR_NAME")
        val model = intent.getStringExtra("CAR_MODEL")
        val year = intent.getIntExtra("CAR_YEAR", 0)
        val price = intent.getIntExtra("CAR_PRICE", 0)
        val engine = intent.getDoubleExtra("CAR_ENGINE", 0.0)
        val imageURL = intent.getStringExtra("CAR_IMAGE")
        val dec  = intent.getStringExtra("CAR_DES")

        findViewById<TextView>(R.id.nameTextView).text = name
        findViewById<TextView>(R.id.modelTextView).text = "Model: $model"
        findViewById<TextView>(R.id.yearTextView).text = "Year: $year"
        findViewById<TextView>(R.id.priceTextView).text = "Price: $$price"
        findViewById<TextView>(R.id.engineTextView).text = "Engine: ${engine}L"
        findViewById<TextView>(R.id.textView3).text = dec
        val imageView = findViewById<ImageView>(R.id.carImageView)
        Picasso.get().load(imageURL).into(imageView)
    }
}
