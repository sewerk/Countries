package pl.srw.countries.common

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

inline fun <reified T: Any> Single<T>.startWithProgress(): Observable<UiState<T>> {
    return this.map { it.toState() }
        .toObservable()
        .startWith(UiState.InProgress)
}

fun Disposable.addTo(disposables: CompositeDisposable) {
    disposables.add(this)
}
