package au.com.telstra.ui.base

import androidx.lifecycle.ViewModelProvider

interface HasViewModelFactory {
    var viewModelFactory: ViewModelProvider.Factory
}
