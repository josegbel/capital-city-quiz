package com.example.capitalcityquizktx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.capitalcityquizktx.databinding.MainActivityBinding

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class SingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<MainActivityBinding>(
            this, R.layout.main_activity)
    }
}
