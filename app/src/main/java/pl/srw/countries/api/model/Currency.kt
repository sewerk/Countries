package pl.srw.countries.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency(
    val name: String?,
    val code: String?,
    val symbol: String?
)