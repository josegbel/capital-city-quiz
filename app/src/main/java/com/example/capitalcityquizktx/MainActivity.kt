package com.example.capitalcityquizktx

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.capitalcityquizktx.databinding.MainActivityBinding
import com.example.capitalcityquizktx.di.HomeModules
import com.example.capitalcityquizktx.business.HomeViewModel
import org.koin.android.ext.android.inject

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class MainActivity : EntryActivity() {

    private val viewModel: HomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<MainActivityBinding>(
            this, R.layout.main_activity)

    }

    override fun loadModules() {
        HomeModules.load()
    }
}
