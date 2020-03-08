package pl.srw.countries.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.srw.countries.MainActivity

@Module
interface AndroidInjectBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    fun contributeYourAndroidInjector(): MainActivity
}