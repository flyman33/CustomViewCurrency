package com.example.customviewcurrency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var custom: CurrencyEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        custom = findViewById(R.id.currency_edit)
        custom.setLimit(10000)

        custom.isEnabled = true
    }
}