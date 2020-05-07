package au.com.telstra.ui.fact

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import au.com.telstra.R
import au.com.telstra.ui.base.BaseFragment
import au.com.telstra.ui.fact.viewModel.FactListViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FactListFragment : BaseFragment() {
    override val contentLayoutResource: Int = R.layout.fact_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(FactListViewModel::class.java)
    }
}
