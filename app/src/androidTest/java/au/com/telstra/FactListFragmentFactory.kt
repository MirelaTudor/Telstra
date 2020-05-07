package au.com.telstra

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import au.com.telstra.ui.fact.FactListFragment
import au.com.telstra.ui.fact.viewModel.FactListViewModel
import io.mockk.every
import io.mockk.mockk

class FactListFragmentFactory : FragmentFactory() {
    val viewModel = mockk<FactListViewModel>(relaxed = true)

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragment = FactListFragment()

        val viewModelFactory = mockk<ViewModelProvider.Factory>()
        every { viewModelFactory.create(FactListViewModel::class.java) } returns viewModel
        fragment.viewModelFactory = viewModelFactory

        return fragment
    }
}
