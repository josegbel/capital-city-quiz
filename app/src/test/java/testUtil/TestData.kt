package testUtil

import com.example.capitalcityquizktx.data.models.geographical.CapitalCity
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.data.models.geographical.continents.*

class TestData {
    companion object {
        val COUNTRY1 =
            Country(
                "Spain",
                CapitalCity("Madrid"),
                Europe
            )
        val COUNTRY2 =
            Country(
                "Senegal",
                CapitalCity("Dakar"),
                Africa
            )
        val COUNTRY3 =
            Country(
                "China",
                CapitalCity("Beijin"),
                Asia
            )
        val COUNTRY4 =
            Country(
                "Australia",
                CapitalCity("Sidney"),
                Australia
            )
        val COUNTRY5 =
            Country(
                "USA",
                CapitalCity("Washington"),
                NorthAmerica
            )
        val COUNTRY6 =
            Country(
                "Peru",
                CapitalCity("Lima"),
                SouthAmerica
            )
        val COUNTRIES = mutableListOf(COUNTRY1, COUNTRY2, COUNTRY3, COUNTRY4, COUNTRY5, COUNTRY6)
    }
}