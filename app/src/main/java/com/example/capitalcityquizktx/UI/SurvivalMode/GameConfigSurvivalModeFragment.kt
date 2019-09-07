package com.example.capitalcityquizktx.UI.SurvivalMode


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.capitalcityquizktx.Config.GameConfig
import com.example.capitalcityquizktx.Config.SurvivalModeGameConfig
import com.example.capitalcityquizktx.Database.Continent
import com.example.capitalcityquizktx.Database.Continents.*
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.databinding.GameConfigSurvivalModeFragmentBinding
import kotlinx.android.synthetic.main.game_config_survival_mode_fragment.*
import kotlinx.android.synthetic.main.title_fragment.*


class GameConfigSurvivalModeFragment : Fragment() {

    private val continentList = arrayListOf<Continent>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: GameConfigSurvivalModeFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_config_survival_mode_fragment, container, false
        )

        // This counter is used to count the amount of countries to set up the seekBar acording to its value
        val counter = MutableLiveData<Int>()
        counter.value = 0

        // This val is used to gather information about the number of chips that are checked
        // in order to show the seekBar if there are any of them checked
        val continentsSelected = MutableLiveData<Int>()
        continentsSelected.value = 0

        // Change the check status of buttons if All continents are selected
        binding.allContinentsSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.africaSurvChip.isChecked = true
                binding.asiaSurvChip.isChecked = true
                binding.australiaSurvChip.isChecked = true
                binding.europeSurvChip.isChecked = true
                binding.northAmericaSurvChip.isChecked = true
                binding.southAmericaSurvChip.isChecked = true
                binding.africaSurvChip.isCheckable = false
                binding.asiaSurvChip.isCheckable = false
                binding.australiaSurvChip.isCheckable = false
                binding.europeSurvChip.isCheckable = false
                binding.northAmericaSurvChip.isCheckable = false
                binding.southAmericaSurvChip.isCheckable = false
            }
            if (!isChecked) {
                binding.africaSurvChip.isCheckable = true
                binding.asiaSurvChip.isCheckable = true
                binding.australiaSurvChip.isCheckable = true
                binding.europeSurvChip.isCheckable = true
                binding.northAmericaSurvChip.isCheckable = true
                binding.southAmericaSurvChip.isCheckable = true
                binding.africaSurvChip.isChecked = false
                binding.asiaSurvChip.isChecked = false
                binding.australiaSurvChip.isChecked = false
                binding.europeSurvChip.isChecked = false
                binding.northAmericaSurvChip.isChecked = false
                binding.southAmericaSurvChip.isChecked = false
            }
        }

        // This observer notifies whether continents are selected so that the UI can display the seekBars
        continentsSelected.observe(this,
            Observer { count ->
                if (count != 0) {
                    binding.selectCountriesNumberTv.isVisible = true
                    binding.countriesNumberSeekBar.isVisible = true
                    binding.selectedCountriesTV.isVisible = true
                    binding.timeLimitSeekBar.isVisible = true
                    binding.selectTimeLimitTv.isVisible = true

                    binding.countriesNumberSeekBar.max = counter.value!!
                    binding.countriesNumberSeekBar.progress = binding.countriesNumberSeekBar.max
                } else {
                    binding.selectCountriesNumberTv.isVisible = false
                    binding.countriesNumberSeekBar.isVisible = false
                    binding.selectedCountriesTV.isVisible = false
                    binding.timeLimitSeekBar.isVisible = false
                    binding.selectTimeLimitTv.isVisible = false
                }
            })

        // These are the listeners that update the message displayed on how many countries and seconds were selected
        binding.countriesNumberSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.selectedCountriesTV.text = "${binding.countriesNumberSeekBar.progress}" +
                        " " + getString(R.string.countries_selected_seek_bar)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not implemented
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not implemented
            }

        })
        binding.timeLimitSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.selectedTimeLimitTV.text = "${binding.timeLimitSeekBar.progress}" +
                        " " + getString(R.string.time_limit_selected_seek_bar)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not implemented
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not implemented
            }

        })

        // TODO Decouple the number of countries in the continents from this class, it should be considered to inject Continent type of objects
        // The following listeners check the state of the chips whether they are checked or not,
        // in order to count the amount of countries and to count the amount of continents selected.
        binding.africaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(Africa.numberOfCountries)
                continentsSelected.value = continentsSelected.value!!.plus(1)
                continentList.add(Africa)
            }else {
                counter.value = counter.value!!.minus(Africa.numberOfCountries)
                continentsSelected.value = continentsSelected.value!!.minus(1)
                continentList.remove(Africa)
            }
        }

        binding.australiaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(Australia.numberOfCountries)
                continentsSelected.value = continentsSelected.value!!.plus(1)
                continentList.add(Australia)
            }else {
                counter.value = counter.value!!.minus(Australia.numberOfCountries)
                continentsSelected.value = continentsSelected.value!!.minus(1)
                continentList.remove(Australia)
            }
        }

        binding.asiaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(Asia.numberOfCountries)
                continentsSelected.value = continentsSelected.value!!.plus(1)
                continentList.add(Asia)
            } else {
                counter.value = counter.value!!.minus(Asia.numberOfCountries)
                continentsSelected.value = continentsSelected.value!!.minus(1)
                continentList.remove(Asia)
            }
        }

        binding.europeSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(Europe.numberOfCountries)
                continentsSelected.value = continentsSelected.value!!.plus(1)
                continentList.add(Europe)
            } else {
                counter.value = counter.value!!.minus(Europe.numberOfCountries)
                continentsSelected.value = continentsSelected.value!!.minus(1)
                continentList.remove(Europe)
            }
        }

        binding.northAmericaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(NorthAmerica.numberOfCountries)
                continentsSelected.value = continentsSelected.value!!.plus(1)
                continentList.add(NorthAmerica)
            } else {
                counter.value = counter.value!!.minus(NorthAmerica.numberOfCountries)
                continentsSelected.value = continentsSelected.value!!.minus(1)
                continentList.remove(NorthAmerica)
            }
        }

        binding.southAmericaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(SouthAmerica.numberOfCountries)
                continentsSelected.value = continentsSelected.value!!.plus(1)
                continentList.add(SouthAmerica)
            } else {
                counter.value = counter.value!!.minus(SouthAmerica.numberOfCountries)
                continentsSelected.value = continentsSelected.value!!.minus(1)
                continentList.remove(SouthAmerica)
            }
        }

        binding.gameConfigSurvPlayBtn.setOnClickListener { v: View ->
            if (continentsSelected.value == 0)
            {
                Toast.makeText(context, getString(R.string.continents_different_to_zero), Toast.LENGTH_SHORT).show()

            }else if(countriesNumberSeekBar.progress == 0){
                Toast.makeText(context, getString(R.string.countries_different_to_zero), Toast.LENGTH_SHORT).show()

            }else if(timeLimitSeekBar.progress == 0){
                Toast.makeText(context, getString(R.string.time_different_to_zero), Toast.LENGTH_SHORT).show()

            }else{
                val gameConfig = SurvivalModeGameConfig(continentList,
                binding.countriesNumberSeekBar.progress,
                binding.timeLimitSeekBar.progress)


                view?.findNavController()?.navigate(GameConfigSurvivalModeFragmentDirections
                    .actionGameConfigSurvivalModeFragmentToSurvivalModeGameFragment(gameConfig))
            }
        }

        return binding.root
    }
}
