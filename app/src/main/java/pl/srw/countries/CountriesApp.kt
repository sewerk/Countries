package pl.srw.countries

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pl.srw.countries.di.DaggerAppComponent
import timber.log.Timber

class CountriesApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<CountriesApp> {
        return DaggerAppComponent.factory().create(this)
    }
}