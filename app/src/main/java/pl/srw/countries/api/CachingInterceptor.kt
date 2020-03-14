package pl.srw.countries.api

import okhttp3.Interceptor
import okhttp3.Response
import pl.srw.countries.Config
import javax.inject.Inject

class CachingInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("Cache-Control", "public, max-age=${Config.cacheTimeout}")
            .build()
        return chain.proceed(request)
    }
}