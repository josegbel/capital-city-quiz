//package com.example.capitalcityquizktx
//
//import androidTestUtils.MainCoroutineRule
//import androidTestUtils.getOrAwaitValue
//import android.content.Context
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Transformations
//import androidx.room.Room
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.platform.app.InstrumentationRegistry
//import com.example.capitalcityquizktx.di.GameUseCasesModule
//import com.example.capitalcityquizktx.di.RepositoryModule
//import com.example.capitalcityquizktx.di.SurvivalViewModelModule
//import com.example.capitalcityquizktx.model.database.*
//import com.example.capitalcityquizktx.model.database.continents.*
//import com.example.capitalcityquizktx.ui.survivalmode.SurvivalViewModel
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import org.junit.After
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.koin.core.context.loadKoinModules
//import org.koin.dsl.module
//import org.koin.test.KoinTest
//import org.koin.test.get
//import java.io.IOException
//import kotlin.random.Random
//
//@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class)
//class SurvivalViewModelAndroidTest : KoinTest {
//
//    @get:Rule
//    val coroutineRule = MainCoroutineRule()
//
//    private lateinit var countryDao : CountryDatabaseDao
//    private lateinit var db: CountryDatabase
//    private lateinit var context : Context
//    private val survivalViewModel = SurvivalViewModel(get(), get())
//
//    companion object{
//        const val TAG : String = "JUnit4"
//    }
//
//    @Before
//    fun setUp() {
//        // Create database
//        context = InstrumentationRegistry.getInstrumentation().targetContext
//        db = Room.inMemoryDatabaseBuilder(context, CountryDatabase::class.java)
//            .allowMainThreadQueries()
//            .build()
//        Log.d(TAG, "createDb")
//        countryDao = db.countryDatabaseDao
//        Log.d(TAG, "Dao Referenced")
//
//        loadKoinModules(module { listOf(
//            SurvivalViewModelModule.getModule(),
//            RepositoryModule.getModule(),
//            GameUseCasesModule.getModules())
//        })
//
////        val application = requireNotNull(activity).application
//    }
//
//    @After
//    @Throws (IOException::class)
//    fun closeDb() {
//        db.close()
//        Log.d(TAG, "closeDb")
//    }
//
//    @Test
//    @Throws (Exception::class)
//    fun should_write_and_read_entry_from_db_in_counties_table(){
//        val country = Country("Spain", CapitalCity("Madrid"), Europe)
//        countryDao.insertAllCountries(listOf(country))
//        Log.d(TAG, "writeDb")
//
//        val countries = countryDao.getCountries()
//        Log.d(TAG, "readDatabase")
//
//        assertEquals(country.countryName, countries.getOrAwaitValue()[0].countryName)
//        assertEquals(country.capitalCity, countries.getOrAwaitValue()[0].capitalCity)
//        assertEquals(country.continent.continentName, countries.getOrAwaitValue()[0].continent.continentName)
//    }
//
//    @Test
//    fun given_list_continents_when_getCountriesByContinent_return_all_their_countries() {
//        val continents = listOf(Europe, Africa, Asia)
//        val expectedSizeList = Europe.totalCountries + Africa.totalCountries + Asia.totalCountries
//        val countries: MutableLiveData<MutableList<Country>>
//
//        survivalViewModel.populateDatabase()
//        Thread.sleep(1500)
//        countries = survivalViewModel.gameUseCases.getCountriesIn(continents)
//        Thread.sleep(1500)
//
//        assertEquals(expectedSizeList, countries.value!!.size)
//    }
//
//    @Test
//    fun should_print_all_european_countries() = coroutineRule.runBlockingTest{
//        survivalViewModel.populateDatabase()
//        Thread.sleep(1500)
//
//        for (i in 0 until Europe.totalCountries)
//            println(survivalViewModel.gameUseCases.getCountriesIn(listOf(Europe)).value!![i].countryName)
//    }
//
//    @Test
//    fun should_print_all_european_countries_in_shuffled_fashion() = coroutineRule.runBlockingTest{
//        val countries : LiveData<List<Country>>? = Transformations.map(survivalViewModel.gameUseCases.getCountriesIn(listOf(Europe))){
//            it.shuffled(Random(System.currentTimeMillis()))
//        }
//
//        survivalViewModel.populateDatabase()
//        Thread.sleep(1500)
//
//        for (i in 0 until Europe.totalCountries)
//            println(countries!!.value!![i].countryName)
//    }
//
//    @Test
//    fun should_print_all_north_american_countries() = coroutineRule.runBlockingTest{
//        survivalViewModel.populateDatabase()
//        Thread.sleep(1500)
//
//        for (i in 0 until NorthAmerica.totalCountries)
//            println(survivalViewModel.gameUseCases.getCountriesIn(listOf(NorthAmerica)).value!![i].countryName)
//    }
//
//    @Test
//    fun should_print_all_south_american_countries() = coroutineRule.runBlockingTest{
//        survivalViewModel.populateDatabase()
//        Thread.sleep(1500)
//
//        for (i in 0 until SouthAmerica.totalCountries)
//            println(survivalViewModel.gameUseCases.getCountriesIn(listOf(SouthAmerica)).value!![i].countryName)
//    }
//
//    @Test
//    fun should_print_all_australian_countries() = coroutineRule.runBlockingTest{
//        survivalViewModel.populateDatabase()
//        Thread.sleep(1500)
//
//        for (i in 0 until Australia.totalCountries)
//            println(survivalViewModel.gameUseCases.getCountriesIn(listOf(Australia)).value!![i].countryName)
//    }
//
//    @Test
//    fun should_print_all_african_countries() = coroutineRule.runBlockingTest{
//        survivalViewModel.populateDatabase()
//        Thread.sleep(1500)
//
//        for (i in 0 until Africa.totalCountries)
//            println(survivalViewModel.gameUseCases.getCountriesIn(listOf(Africa)).value!![i].countryName)
//    }
//
//    @Test
//    fun should_print_all_asian_countries() = coroutineRule.runBlockingTest{
//        survivalViewModel.populateDatabase()
//        Thread.sleep(1500)
//
//        for (i in 0 until Asia.totalCountries)
//            println(survivalViewModel.gameUseCases.getCountriesIn(listOf(Asia)).value!![i].countryName)
//    }
//
//    @Test
//    @Throws (Exception::class)
//    fun should_write_and_read_many_entries_from_db_in_counties_table(){
//        val country1 = Country("Spain", CapitalCity("Madrid"), Europe)
//        val country2 = Country("Senegal", CapitalCity("Dakar"), Africa)
//        val country3 = Country("China", CapitalCity("Beijin"), Asia)
//        val country4 = Country("Australia", CapitalCity("Sidney"), Australia)
//        val country5 = Country("USA", CapitalCity("Washington"), NorthAmerica)
//        val country6 = Country("Peru", CapitalCity("Lima"), SouthAmerica)
//        val expected = listOf(country1, country2, country3,
//                                            country4, country5, country6)
//        countryDao.insertAllCountries(expected)
//        Log.d(TAG, "writeDb")
//
//        val actual = countryDao.getCountries()
//        Log.d(TAG, "readDatabase")
//
//        for ((i, country) in expected.withIndex()){
//            assertEquals(
//                country.countryName, actual.getOrAwaitValue()[i].countryName)
//            assertEquals(
//                country.capitalCity, actual.getOrAwaitValue()[i].capitalCity)
//            assertEquals(
//                country.continent.continentName, actual.getOrAwaitValue()[i].continent.continentName
//            )
//        }
//    }
//
//
//    @Test
//    @Throws (Exception::class)
//    fun should_write_and_read_entry_from_db_in_countriesLearned_table(){
//        val country = Country("Spain", CapitalCity("Madrid"), Europe)
//        val user = User(1,
//                     "johnDoe",
//                     "pw",
//                     "john",
//                      "doe",
//                         "john@doe.com")
//
//        countryDao.insertLearnedCountry(LearnedCountry(user.userId, country))
//        Log.d(TAG, "writeToLearnedTable")
//        val countries = countryDao.getLearnedCountries()
//        Log.d(TAG, "readFromLearnedTable")
//        assertEquals(country.countryName,             countries.getOrAwaitValue()[0].country.countryName)
//        assertEquals(country.capitalCity,             countries.getOrAwaitValue()[0].country.capitalCity)
//        assertEquals(country.continent.continentName, countries.getOrAwaitValue()[0].country.continent.continentName)
//        assertEquals(user.userId,                     countries.getOrAwaitValue()[0].userId)
//    }
//
//    @Test
//    fun should_delete_entries_in_countries_table(){
//        // insert Country
//        val country = Country("Spain", CapitalCity("Madrid"), Europe)
//        countryDao.insertAllCountries(listOf(country))
//        Log.d(TAG, "writeDb")
//        var countries = countryDao.getCountries()
//        Log.d(TAG, "readDatabase")
//        // assert country was correctly inserted
//        assertEquals(country.countryName, countries.getOrAwaitValue()[0].countryName)
//
//        // when destroy countries is called
//        countryDao.destroyCountries()
//        countries = countryDao.getCountries()
//        val emptyList = emptyList<Country>()
//
//        // then assert the country list from database is empty
//        assertEquals(emptyList ,countries.getOrAwaitValue())
//    }
//
//    @Test
//    fun given_full_database_when_view_model_is_initialised_then_delete_entries_and_insert_all_again() = coroutineRule.runBlockingTest{
//        // thread sleep is a workaround to the problem with coroutines
//        survivalViewModel.gameUseCases.destroyCountries()
//        Thread.sleep(1000)
//
//        val country1 = Country(   "Spain", CapitalCity("Madrid"), Europe)
//        val country2 = Country(  "France", CapitalCity("Paris"),  Europe)
//        val country3 = Country("Portugal", CapitalCity("Lisbon"), Europe)
//        val expectedCountries = listOf(country1, country2, country3)
//        survivalViewModel.gameUseCases.insertAllCountries(expectedCountries)
//        Log.d(TAG, "write3EntriesInDb")
//        assertEquals(3, survivalViewModel.gameUseCases.getDataFieldsCount())
//
//        survivalViewModel.populateDatabase()
//        Thread.sleep(1000)
//
//        assertEquals(195, survivalViewModel.gameUseCases.getDataFieldsCount())
//    }
//
//    fun when_database_is_populated_print_elements_for_debugging_purposes_only() = coroutineRule.runBlockingTest{
//        survivalViewModel.populateDatabase()
//        val countriesObservable = survivalViewModel.gameUseCases.getAllCountries()
//        countriesObservable.subscribe { countries ->
//            for (i in 0..194){
//                println("${countries[i].countryName}," +
//                        "${countries[i].capitalCity.name}," +
//                        "${countries[i].continent.continentName}"
//                )
//
//            }
//        }
//    }
//}