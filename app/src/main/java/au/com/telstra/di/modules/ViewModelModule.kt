package au.com.telstra.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import au.com.telstra.di.ViewModelKey
import au.com.telstra.ui.base.ViewModelFactory
import au.com.telstra.ui.fact.viewModel.FactListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Module responsible for ViewModel multibinding. Each injectable ViewModel needs to be defined here.
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FactListViewModel::class)
    internal abstract fun bindFactListViewModel(factListViewModel: FactListViewModel): ViewModel
}
