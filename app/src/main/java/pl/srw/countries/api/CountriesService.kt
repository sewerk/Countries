package pl.srw.countries.api

import io.reactivex.Single
import pl.srw.countries.api.model.Country
import retrofit2.http.GET

interface CountriesService {

    @GET("all?fields=name;topLevelDomain;callingCodes;currencies")
    fun getCountries(): Single<List<Country>>
}