package com.example.capitalcityquizktx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.capitalcityquizktx.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<MainActivityBinding>(
            this, R.layout.main_activity)


    }
}
