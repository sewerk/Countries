package pl.srw.countries.repository

import pl.srw.countries.api.CountriesService
import javax.inject.Inject

class CountryRepo @Inject constructor(
    private val service: CountriesService
) {

    fun getCountries() = service.getCountries()
}