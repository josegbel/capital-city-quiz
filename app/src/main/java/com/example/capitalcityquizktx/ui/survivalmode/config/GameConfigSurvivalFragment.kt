package com.example.capitalcityquizktx.ui.survivalmode.config

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.data.models.config.SurvivalGameConfig
import com.example.capitalcityquizktx.databinding.GameConfigSurvivalFragmentBinding
import com.example.capitalcityquizktx.domain.GameConfigSurvivalPresenter
import com.example.capitalcityquizktx.data.models.geographical.Continent
import com.example.capitalcityquizktx.data.models.geographical.continents.*
import kotlinx.android.synthetic.main.game_config_survival_fragment.*
import kotlin.time.ExperimentalTime

/**

    J. Garcia CapitalCityQuiz in Kotlin 2019

 */
class GameConfigSurvivalFragment : Fragment(),
    GameConfigSurvivalView {

    override var minTimeLimit = 5 // seconds per question

    override var maxTimeLimit = 15 // seconds per question

    override val continentsList = MutableLiveData<List<Continent>>().default(arrayListOf())

    private val _numberOfCountries = MutableLiveData<Int>().default(0)

    override val numberOfCountries : LiveData<Int> = _numberOfCountries

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

    @ExperimentalTime
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: GameConfigSurvivalFragmentBinding = inflate(
            inflater, R.layout.game_config_survival_fragment, container, false
        )

        val presenter = GameConfigSurvivalPresenter(this)
        presenter.receiveContinentSelection()


        // This counter is used to count the amount of countries to set up the seekBar acording to its value
        val counter = MutableLiveData<Int>()
        counter.value = 0

        // This val is used to gather information about the number of chips that are checked
        // in order to show the seekBar if there are any of them checked
        val continentsSelected = MutableLiveData<Int>()
        continentsSelected.value = 0

        binding.timeLimitSeekBar.progress = maxTimeLimit

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
                allContinentsSurvChip.chipStrokeWidth = 6.0f
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
                allContinentsSurvChip.chipStrokeWidth = 0.0f
            }
        }

        displayQuestionNumberSeekBar.observe(viewLifecycleOwner,
            Observer { displayIt ->
                if (displayIt){
                    binding.selectCountriesNumberTv.isVisible = true

                    binding.countriesNumberSeekBar.isVisible = true

                    binding.selectedCountriesTV.isVisible = true

                    binding.countriesNumberSeekBar.max = counter.value!!

                    binding.countriesNumberSeekBar.progress = binding.countriesNumberSeekBar.max
                }
                else{
                    binding.selectCountriesNumberTv.isVisible = false

                    binding.countriesNumberSeekBar.isVisible = false

                    binding.selectedCountriesTV.isVisible = false
                }
            })

        displayTimeLimitSeekBar.observe(viewLifecycleOwner,
            Observer { displayIt ->
                if (displayIt){
                    binding.selectTimeLimitTv.isVisible = true

                    binding.timeLimitSeekBar.isVisible = true

                    binding.timeLimitTv.isVisible = true

                    // *1000 = convertToMilliseconds     *5 = steps of SeekBar
                    val timeLimit = presenter
                        .formatTime(((binding.countriesNumberSeekBar.progress
                                * (binding.timeLimitSeekBar.progress + minTimeLimit)*1000*5)
                            .toLong()))

                    binding.timeLimitTv.text = "$timeLimit"
                }
                else{
                    binding.selectTimeLimitTv.isVisible = false

                    binding.timeLimitSeekBar.isVisible = false

                    binding.timeLimitTv.isVisible = false
                }
            })

        // These are the listeners that update the message displayed on how many countries and seconds were selected
        binding.countriesNumberSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.selectedCountriesTV.text = getString(R.string.countries, progress)

                minTimeLimit = 2

                maxTimeLimit = 6 - minTimeLimit

                timeLimitSeekBar.max = maxTimeLimit

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

                val timeLimit = presenter
                    .formatTime(((binding.countriesNumberSeekBar.progress
                            * (binding.timeLimitSeekBar.progress + minTimeLimit)*1000*5).toLong()))

                binding.timeLimitTv.text = "$timeLimit"

                // progress + minimum range value * steps of SeekBar
                binding.selectTimeLimitTv.text = getString(R.string.seconds_per_question, ((progress+minTimeLimit)*5))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not implemented
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not implemented
            }

        })

        //TODO The logic contained bellow should be decoupled from the view

        /* The following listeners check the state of the chips whether they are checked or not,
        in order to count the amount of countries and to count the amount of continents selected */

        binding.africaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(Africa.totalCountries)

                continentsList.add(Africa)

                africaSurvChip.chipStrokeWidth = 6.0f
            }else {
                counter.value = counter.value!!.minus(Africa.totalCountries)

                continentsList.remove(Africa)

                africaSurvChip.chipStrokeWidth = 0.0f
            }
        }

        binding.australiaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(Australia.totalCountries)

                continentsList.add(Australia)

                australiaSurvChip.chipStrokeWidth = 6.0f
            }else {
                counter.value = counter.value!!.minus(Australia.totalCountries)

                continentsList.remove(Australia)

                australiaSurvChip.chipStrokeWidth = 0.0f
            }
        }

        binding.asiaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(Asia.totalCountries)

                continentsList.add(Asia)

                asiaSurvChip.chipStrokeWidth = 6.0f
            } else {
                counter.value = counter.value!!.minus(Asia.totalCountries)

                continentsList.remove(Asia)

                asiaSurvChip.chipStrokeWidth = 0.0f
            }
        }

        binding.europeSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(Europe.totalCountries)

                continentsList.add(Europe)

                europeSurvChip.chipStrokeWidth = 6.0f

                Log.d("ChipSelected", "EuropeChipSelected: isChecked: ${europeSurvChip.isChecked}")
            } else {
                counter.value = counter.value!!.minus(Europe.totalCountries)

                continentsList.remove(Europe)

                europeSurvChip.chipStrokeWidth = 0.0f

                Log.d("ChipSelected", "EuropeChipSelected: isChecked: ${europeSurvChip.isChecked}")
            }
        }

        binding.northAmericaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(NorthAmerica.totalCountries)

                continentsList.add(NorthAmerica)

                northAmericaSurvChip.chipStrokeWidth = 6.0f
            } else {
                counter.value = counter.value!!.minus(NorthAmerica.totalCountries)
                continentsList.remove(NorthAmerica)


                northAmericaSurvChip.chipStrokeWidth = 0.0f
            }
        }

        binding.southAmericaSurvChip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                counter.value = counter.value!!.plus(SouthAmerica.totalCountries)

                continentsList.add(SouthAmerica)

                southAmericaSurvChip.chipStrokeWidth = 6.0f
            } else {
                counter.value = counter.value!!.minus(SouthAmerica.totalCountries)

                continentsList.remove(SouthAmerica)

                southAmericaSurvChip.chipStrokeWidth = 0.0f
            }
        }

        // Launch the game!
        binding.gameConfigSurvPlayBtn.setOnClickListener { v: View ->
            if (continentsList.value!!.isEmpty()) {
                Toast.makeText(context, getString(R.string.continents_different_to_zero), Toast.LENGTH_SHORT).show()
            }
            else if (countriesNumberSeekBar.progress == 0){
                Toast.makeText(context, getString(R.string.countries_different_to_zero), Toast.LENGTH_SHORT).show()
            }
            else {
                val gameConfig = SurvivalGameConfig(continentsList.value as ArrayList<Continent>,
                    binding.countriesNumberSeekBar.progress,
                    binding.timeLimitSeekBar.progress + minTimeLimit)

                view?.findNavController()?.navigate(
                    GameConfigSurvivalFragmentDirections.actionGameConfigSurvivalFragmentToSurvivalGameFragment(
                        gameConfig
                    )
                )
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
