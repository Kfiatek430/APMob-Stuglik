package com.example.lekcja_06

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.lekcja_06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MyActivity"
    private var counter = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvCounter: TextView = findViewById<TextView>(R.id.textView1)
        val btnIncrement: Button = findViewById<Button>(R.id.button1)

        Log.i(TAG, "onCreate: ")

        tvCounter.text = counter.toString();

        btnIncrement.setOnClickListener {
            Log.i(TAG, "Button clicked")
            counter++
            tvCounter.text = counter.toString()
        }

        binding.button2.setOnClickListener {
            Log.i(TAG, "Button clicked")
            counter--
            binding.textView1.text = counter.toString()
        }
    }
    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart: ")
    }
    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }
    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }
    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }
}