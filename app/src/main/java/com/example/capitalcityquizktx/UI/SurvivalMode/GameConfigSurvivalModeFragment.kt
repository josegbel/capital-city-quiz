package com.example.capitalcityquizktx.UI.SurvivalMode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.capitalcityquizktx.Config.SurvivalModeGameConfig
import com.example.capitalcityquizktx.Database.Continent
import com.example.capitalcityquizktx.GameConfigSurvivalModePresenter
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.databinding.GameConfigSurvivalModeFragmentBinding
import kotlinx.android.synthetic.main.game_config_survival_mode_fragment.*

/*

    J. Garcia CapitalCityQuiz in Kotlin 2019

 */
class GameConfigSurvivalModeFragment : Fragment(), GameConfigSurvivalModeView {

    override val continentsList = MutableLiveData<List<Continent>>().default(arrayListOf())
    private val displayTimeLimitSeekBar = MutableLiveData<Boolean>().default(false)
    private val displayQuestionNumberSeekBar = MutableLiveData<Boolean>().default(false)

    override fun showQuestionsNumberSelection() {
        displayQuestionNumberSeekBar.value = true
    }

    override fun showTimeLimitSelection() {
        displayTimeLimitSeekBar.value = true
    }

    override fun hideQuestionsNumberSelection() {
        displayQuestionNumberSeekBar.value = false
    }

    override fun hideTimeLimitSelection() {
        displayTimeLimitSeekBar.value = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: GameConfigSurvivalModeFragmentBinding = inflate(
            inflater, R.layout.game_config_survival_mode_fragment, container, false
        )

        val presenter = GameConfigSurvivalModePresenter(this)
        presenter.receiveContinentSelection()

        //Minimum amount of seconds that will be added to timeLimitSeekbar
        val minTimeLimit = 5

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

        displayQuestionNumberSeekBar.observe(this,
            Observer { displayIt ->
                if (displayIt){
                    binding.selectCountriesNumberTv.isVisible = true
                    binding.countriesNumberSeekBar.isVisible = true
                    binding.selectedCountriesTV.isVisible = true
                    binding.countriesNumberSeekBar.max = counter.value!!
                    binding.countriesNumberSeekBar.progress = binding.countriesNumberSeekBar.max
                }else{
                    binding.selectCountriesNumberTv.isVisible = false
                    binding.countriesNumberSeekBar.isVisible = false
                    binding.selectedCountriesTV.isVisible = false
                }
            })

        displayTimeLimitSeekBar.observe(this,
            Observer { displayIt ->
                if (displayIt){
                    binding.selectTimeLimitTv.isVisible = true
                    binding.timeLimitSeekBar.isVisible = true
                    binding.timeLimitTv.isVisible = true
                }else{
                    binding.selectTimeLimitTv.isVisible = false
                    binding.timeLimitSeekBar.isVisible = false
                    binding.timeLimitTv.isVisible = false
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
                binding.timeLimitTv.text = "${binding.timeLimitSeekBar.progress + minTimeLimit}" +
                        " " + getString(R.string.time_limit_selected_seek_bar)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not implemented
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not implemented
            }

        })

        //TODO Refactor the code below to modify constants with variables once the database is connected to the system.

        // The following listeners check the state of the chips whether they are checked or not,
        // in order to count the amount of countries and to count the amount of continents selected.
        binding.africaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(54)
                continentsList.add(Continent("Africa", 54))
            }else {
                counter.value = counter.value!!.minus(54)
                continentsList.remove(Continent("Africa", 54))
            }
        }
        binding.australiaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
//                counter.value = counter.value!!.plus(Australia.numberOfCountries)
//                continentsList.add(Australia)
                counter.value = counter.value!!.plus(14)
                continentsList.add(Continent("Australia", 14))

            }else {
                counter.value = counter.value!!.minus(14)
                continentsList.remove(Continent("Australia", 14))
            }
        }
        binding.asiaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(44)
                continentsList.add(Continent("Asia", 44))
            } else {
                counter.value = counter.value!!.minus(44)
                continentsList.remove(Continent("Asia", 44))
            }
        }
        binding.europeSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(50)
                continentsList.add(Continent("Europe", 50))
            } else {
                counter.value = counter.value!!.minus(50)
                continentsList.remove(Continent("Europe", 50))
            }
        }
        binding.northAmericaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(7)
                continentsList.add(Continent("NorthAmerica", 7))
            } else {
                counter.value = counter.value!!.minus(7)
                continentsList.remove(Continent("NorthAmerica", 7))
            }
        }
        binding.southAmericaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(28)
                continentsList.add(Continent("SouthAmerica", 28))
            } else {
                counter.value = counter.value!!.minus(28)
                continentsList.remove(Continent("SouthAmerica", 28))
            }
        }

        // Launch the game!
        binding.gameConfigSurvPlayBtn.setOnClickListener { v: View ->
            if (continentsList.value!!.size == 0) {
                Toast.makeText(context, getString(R.string.continents_different_to_zero), Toast.LENGTH_SHORT).show()

            }else if(countriesNumberSeekBar.progress == 0){
                Toast.makeText(context, getString(R.string.countries_different_to_zero), Toast.LENGTH_SHORT).show()

            }else{
                val gameConfig = SurvivalModeGameConfig(continentsList.value as ArrayList<Continent>,
                    binding.countriesNumberSeekBar.progress,
                    binding.timeLimitSeekBar.progress + minTimeLimit)

                view?.findNavController()?.navigate(GameConfigSurvivalModeFragmentDirections
                    .actionGameConfigSurvivalModeFragmentToSurvivalModeGameFragment(gameConfig))
            }
        }
        return binding.root
    }
}

/*

    The followings are extension functions to manipulate MutableLiveData

 */
private fun <T> MutableLiveData<T>.default(initialValue: T) = apply { value = initialValue }

private fun <T> MutableLiveData<List<T>>.remove(item: T) {
    val updatedItems = this.value as ArrayList
    updatedItems.remove(item)
    this.value = updatedItems
}

private fun <T> MutableLiveData<List<T>>.add(item: T) {
    val updatedItems = this.value as ArrayList
    updatedItems.add(item)
    this.value = updatedItems
}
