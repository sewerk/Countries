package pl.srw.countries.ui.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.srw.countries.api.model.Country
import pl.srw.countries.common.UiState
import pl.srw.countries.common.addTo
import pl.srw.countries.common.startWithProgress
import pl.srw.countries.repository.CountryRepo
import javax.inject.Inject

typealias CountriesState = UiState<List<Country>>

class ListViewModel @Inject constructor(
    countryRepo: CountryRepo
) : ViewModel() {

    val countries = MutableLiveData<CountriesState>()

    private val disposables = CompositeDisposable()

    init {
        countryRepo.getCountries()
            .subscribeOn(Schedulers.io())
            .startWithProgress()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                this::onNext,
                this::handleError
            ).addTo(disposables)
    }

    private fun onNext(it: CountriesState) {
        countries.value = it
    }

    private fun handleError(throwable: Throwable) {
        Log.e("ListViewModel", "Countries fetch failed", throwable)
        countries.value = UiState.Error(throwable.message)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
