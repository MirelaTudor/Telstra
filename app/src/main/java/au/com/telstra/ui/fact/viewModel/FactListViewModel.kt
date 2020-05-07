package au.com.telstra.ui.fact.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import au.com.telstra.ui.fact.FactView
import javax.inject.Inject

class FactListViewModel @Inject constructor() : ViewModel() {

    // facListLiveData in charge to update the recycler view content
    val facListLiveData = MutableLiveData<List<FactView.DataModel>>()
    // titleLiveData used to update the title of the fact, displayed in toolbar
    val titleLiveData = MutableLiveData<String>()
    // errorMessageResLiveData used to notify the view if an error took place
    val errorMessageResLiveData = MutableLiveData<Int>()

    fun refresh() {

    }
}

