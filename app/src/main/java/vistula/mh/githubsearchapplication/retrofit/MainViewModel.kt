package vistula.mh.githubsearchapplication.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import vistula.mh.githubsearchapplication.model.GithubModel

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse : MutableLiveData<Response<GithubModel>> = MutableLiveData()

    fun getRepositoryByName(name: String){
        viewModelScope.launch {
            val response = repository.getRepositoryByName(name)
            myResponse.value = response
        }
    }
}