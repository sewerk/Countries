package pl.srw.countries.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.srw.countries.CountriesApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectBuilder::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<CountriesApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<CountriesApp>
}
