package pl.srw.countries

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pl.srw.countries.di.DaggerAppComponent

class CountriesApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<CountriesApp> {
        return DaggerAppComponent.factory().create(this)
    }
}