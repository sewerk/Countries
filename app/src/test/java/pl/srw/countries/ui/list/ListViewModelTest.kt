package pl.srw.countries.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.subjects.SingleSubject
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Rule
import org.junit.Test
import pl.srw.countries.api.model.Country
import pl.srw.countries.common.UiState
import pl.srw.countries.repository.CountryRepo
import pl.srw.countries.util.RxRule
import java.io.IOException

class ListViewModelTest {

    @get:Rule
    val rxRule = RxRule()
    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    val countriesResponse = SingleSubject.create<List<Country>>()
    val repository: CountryRepo = mock {
        on { getCountries() } doReturn countriesResponse
    }

    val tested by lazy { ListViewModel(repository) }

    @Test
    fun `when created then in progress state is returned`() {
        // when
        val result = tested.countries.value!!

        // then
        result shouldBeInstanceOf UiState.InProgress::class
    }

    @Test
    fun `when created then countries are being retrieved from the repository`() {
        // when
        tested

        // then
        countriesResponse.hasObservers() shouldBe true
    }

    @Test
    fun `given repository returns result when created then success state is returned`() {
        // given
        val countries = listOf(Country())
        countriesResponse.onSuccess(countries)

        // when
        val result = tested.countries.value!!

        // then
        result shouldBeInstanceOf UiState.Success::class
        (result as UiState.Success<List<Country>>).data shouldBeEqualTo countries
    }

    @Test
    fun `given repository returns error when created then error state is returned`() {
        // given
        countriesResponse.onError(IOException())

        // when
        val result = tested.countries.value!!

        // then
        result shouldBeInstanceOf UiState.Error::class
    }
}