package com.hussain.weatherassessmenttest.Activity

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hussain.weatherassessmenttest.R;
import com.hussain.weatherassessmenttest.databinding.ActivityWeatherBinding
import com.hussain.weatherassessmenttest.viewmodel.WeatherViewModel


class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupViewModel()
        fetchWeatherData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val userName = intent.getStringExtra("user_name")
        supportActionBar?.title = "Weather for $userName"
    }

    private fun setupViewModel() {
        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        weatherViewModel.loading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

//        weatherViewModel.error.observe(this) { error ->
//            if (error != null) {
//                binding.tvError.text = error
//                binding.tvError.visibility = View.VISIBLE
//                binding.cardWeather.visibility = View.GONE
//            } else {
//                binding.tvError.visibility = View.GONE
//            }
//        }
        weatherViewModel.error.observe(this) { error ->
            if (error != null) {
                binding.tvError.text = error
                binding.tvError.visibility = View.VISIBLE
                binding.cardWeather.visibility = View.GONE

                // Correct way to handle text scrolling
                binding.tvError.movementMethod = ScrollingMovementMethod()
              //  binding.tvError.horizontalScrollBarEnabled = false
                binding.tvError.maxLines = 10 // or Integer.MAX_VALUE for unlimited lines
            } else {
                binding.tvError.visibility = View.GONE
            }
        }

        weatherViewModel.weatherData.observe(this) { weatherResponse ->
            if (weatherResponse != null && weatherResponse.current != null) {
                displayWeatherData(weatherResponse)
                binding.cardWeather.visibility = View.VISIBLE
                binding.tvError.visibility = View.GONE
            }
        }
    }

    private fun fetchWeatherData() {
        weatherViewModel.fetchWeather()
    }

    private fun displayWeatherData(weatherResponse: com.hussain.weatherassessmenttest.data.WeatherResponse) {
        val current = weatherResponse.current

        binding.tvTemperature.text = "${current.temp.toInt()}°F"
        binding.tvHumidity.text = "${current.humidity}%"
        binding.tvWindSpeed.text = "${current.wind_speed} mph"

        if (current.weather.isNotEmpty()) {
            binding.tvWeatherType.text = current.weather[0].main
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.weather_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        val intent = Intent(this, OnboardingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}