package pl.srw.countries.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.list_fragment.*
import pl.srw.countries.R
import pl.srw.countries.common.UiState
import pl.srw.countries.common.ViewModelFactory
import pl.srw.countries.common.exhaustive
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var vmFactory: ViewModelFactory<ListViewModel>

    private val viewModel: ListViewModel by viewModels { vmFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.list_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = CountriesAdapter()
        list.adapter = adapter
        viewModel.countries.observe(viewLifecycleOwner) {
            handleUiState(it, adapter)
        }
    }

    private fun handleUiState(
        it: CountriesState?,
        adapter: CountriesAdapter
    ) {
        var inProgress = View.GONE
        when (it) {
            is UiState.Success -> adapter.data = it.data
            is UiState.Error -> toast(it.errorMessage)
            UiState.InProgress, null -> inProgress = View.VISIBLE
        }.exhaustive
        progress_bar.visibility = inProgress
    }

    private fun toast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}
