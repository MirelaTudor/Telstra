package au.com.telstra.ui.fact.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.com.telstra.R
import au.com.telstra.data.domain.FactRepository
import au.com.telstra.ui.fact.FactView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class FactListViewModel @Inject constructor(
    private val factRepository: FactRepository
) : ViewModel() {

    // facListLiveData in charge to update the recycler view content
    val facListLiveData = MutableLiveData<List<FactView.DataModel>>()
    // titleLiveData used to update the title of the fact, displayed in toolbar
    val titleLiveData = MutableLiveData<String>()
    // errorMessageResLiveData used to notify the view if an error took place
    val errorMessageResLiveData = MutableLiveData<Int>()

    init {
        loadFacts()
        refresh()
    }

    // method used to retrieve the data from networking
    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                factRepository.refreshFacts()
            } catch (e: Exception) {
                val message = if (e is IOException)
                    R.string.no_internet_connection
                else R.string.default_error_message

                errorMessageResLiveData.postValue(message)
            }
        }
    }

    // load the data from caching, if exists
    private fun loadFacts() {
        viewModelScope.launch(Dispatchers.Main) {
            factRepository.getFacts().collect {
                titleLiveData.postValue(it.title)
                facListLiveData.postValue(it.facts.map { entity ->
                    FactView.DataModel(entity.title, entity.description, entity.imageUrl)
                })
            }
        }
    }
}

