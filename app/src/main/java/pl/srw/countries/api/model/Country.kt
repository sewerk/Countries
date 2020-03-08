package pl.srw.countries.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    val name: String = "",
    @Json(name = "topLevelDomain") val domains: List<String> = emptyList(),
    @Json(name = "callingCodes") val phoneCodes: List<String> = emptyList(),
    val currencies: List<Currency> = emptyList()
)