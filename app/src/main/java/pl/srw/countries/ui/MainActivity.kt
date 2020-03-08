package pl.srw.countries.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import pl.srw.countries.R
import pl.srw.countries.ui.list.ListFragment

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commitNow()
        }
    }
}
