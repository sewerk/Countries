package pl.srw.countries

private const val DAY_IN_SECONDS = 60 * 60 * 24
private const val MB_2 = 2L * 1024 * 1024

object Config {

    const val cacheSize = MB_2
    const val cacheTimeout = DAY_IN_SECONDS
    const val baseUrl = "https://restcountries.eu/rest/v2/"
}