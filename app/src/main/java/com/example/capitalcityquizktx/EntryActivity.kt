package com.example.capitalcityquizktx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
abstract class EntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModules()
    }

    protected abstract fun loadModules()

}