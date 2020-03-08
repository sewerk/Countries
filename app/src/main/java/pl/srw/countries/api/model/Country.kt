package pl.srw.countries.api.model

import com.squareup.moshi.Json

data class Country(
    val name: String = "",
    @Json(name = "topLevelDomain") val domains: List<String> = emptyList(),
    @Json(name = "callingCodes") val phoneCodes: List<String> = emptyList(),
    val currencies: List<Currency> = emptyList()
)