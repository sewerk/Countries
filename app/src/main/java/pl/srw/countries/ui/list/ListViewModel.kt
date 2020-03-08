package pl.srw.countries.ui.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pl.srw.countries.api.model.Country
import pl.srw.countries.repository.CountryRepo
import javax.inject.Inject

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
                this::onSuccess,
                this::handleError
            ).addTo(disposables)
    }

    private fun onSuccess(it: List<Country>?) {
        countries.value = it
    }

    private fun handleError(throwable: Throwable?) {
        Log.e("ListViewModel", "Countries fetch failed", throwable)
        // TODO: proper error handling requires passing state to UI
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}

private fun Disposable.addTo(disposables: CompositeDisposable) {
    disposables.add(this)
}
