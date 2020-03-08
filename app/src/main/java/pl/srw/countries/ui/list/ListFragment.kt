package pl.srw.countries.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.list_fragment.*
import pl.srw.countries.R
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject lateinit var vmFactory: ListViewModel.Factory

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
            progress_bar.visibility = if (it != null) View.GONE else View.VISIBLE
            adapter.data = it
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}
