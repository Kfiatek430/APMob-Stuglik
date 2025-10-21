package com.example.shapecolorpicker

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var spinner: Spinner
    private lateinit var redSlider: SeekBar
    private lateinit var greenSlider: SeekBar
    private lateinit var blueSlider: SeekBar
    private lateinit var rgbText: TextView

    private var red = 255
    private var green = 255
    private var blue = 255

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageView = findViewById(R.id.imageView)
        spinner = findViewById(R.id.mySpinner)
        redSlider = findViewById(R.id.redSlider)
        greenSlider = findViewById(R.id.greenSlider)
        blueSlider = findViewById(R.id.blueSlider)
        rgbText = findViewById(R.id.rgbText)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: android.view.View, position: Int, id: Long
            ) {
                when (position) {
                    0 -> imageView.setImageResource(R.drawable.circle)
                    1 -> imageView.setImageResource(R.drawable.square)
                    2 -> imageView.setImageResource(R.drawable.triangle)
                }
                applyColor()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val listener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (seekBar?.id) {
                    R.id.redSlider -> red = progress
                    R.id.greenSlider -> green = progress
                    R.id.blueSlider -> blue = progress
                }
                applyColor()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        }

        redSlider.setOnSeekBarChangeListener(listener)
        greenSlider.setOnSeekBarChangeListener(listener)
        blueSlider.setOnSeekBarChangeListener(listener)
    }

    private fun applyColor() {
        val color = Color.rgb(red, green, blue)
        imageView.setColorFilter(color)
        rgbText.text = "RGB: $red, $green, $blue"
    }
}
