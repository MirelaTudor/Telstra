package au.com.telstra.ui.fact

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import au.com.telstra.R
import au.com.telstra.ui.base.BaseFragment
import au.com.telstra.ui.fact.viewModel.FactListViewModel
import kotlinx.android.synthetic.main.fact_list_fragment.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FactListFragment : BaseFragment() {
    override val contentLayoutResource: Int = R.layout.fact_list_fragment

    private lateinit var factsAdapter: FactsAdapter
    private lateinit var viewModel: FactListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setUpViewModel()
        setListeners()
    }

    /**
     * set up the ui components
     * add adapter for the recycler view
     */
    private fun setUpView() {
        swipe_refresh_facts.isRefreshing = true
        factsAdapter = FactsAdapter()
        fact_list.apply {
            adapter = factsAdapter
            isNestedScrollingEnabled = false
        }
    }

    /**
     * set listener for the pull down to refresh
     */
    private fun setListeners() {
        swipe_refresh_facts.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    /**
     * set up the view model and the observers for the live data
     */
    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(FactListViewModel::class.java)

        viewModel.titleLiveData.observe(viewLifecycleOwner) {
            toolbar.title = it
        }

        viewModel.facListLiveData.observe(viewLifecycleOwner) {
            swipe_refresh_facts.isRefreshing = false
            factsAdapter.submitList(it)
        }

        viewModel.errorMessageResLiveData.observe(viewLifecycleOwner) {
            swipe_refresh_facts.isRefreshing = false
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }
}
