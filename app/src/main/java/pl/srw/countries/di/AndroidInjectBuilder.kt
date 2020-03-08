package pl.srw.countries.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.srw.countries.ui.MainActivity
import pl.srw.countries.ui.list.ListFragment

@Module
interface AndroidInjectBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun contributeMainActivity(): MainActivity
}

@Module
interface MainActivityModule {

    @ContributesAndroidInjector
    fun contributeListFragment(): ListFragment
}