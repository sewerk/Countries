package pl.srw.countries.ui.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pl.srw.countries.api.model.Country
import pl.srw.countries.repository.CountryRepo
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

class ListViewModel @Inject constructor(
    countryRepo: CountryRepo
) : ViewModel() {

    val countries = MutableLiveData<List<Country>>()

    private val disposables = CompositeDisposable()

    init {
        countryRepo.getCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { countries.value = it },
                { Log.e("ListViewModel", "Countries fetch failed", it) }
            ).addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    @Singleton
    class Factory @Inject constructor(
        private val vm: Provider<ListViewModel>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) = vm.get() as T
    }
}

private fun Disposable.addTo(disposables: CompositeDisposable) {
    disposables.add(this)
}
