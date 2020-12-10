package vistula.mh.githubsearchapplication.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import vistula.mh.githubsearchapplication.model.commits.CommitModelItem
import vistula.mh.githubsearchapplication.model.repository.GithubModel

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse : MutableLiveData<Response<GithubModel>> = MutableLiveData()
    val commitResponse : MutableLiveData<Response<List<CommitModelItem>>> = MutableLiveData()

    fun getRepositoryByName(name: String){
        viewModelScope.launch {
            val response = repository.getRepositoryByName(name)
            myResponse.value = response
        }
    }

    fun getLastCommits(login: String, name: String){
        viewModelScope.launch {
            val response = repository.getLastCommits(login, name)
            commitResponse.value = response
        }
    }
}