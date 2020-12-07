package vistula.mh.githubsearchapplication.retrofit

import retrofit2.Response
import vistula.mh.githubsearchapplication.model.GithubModel

class Repository {

    suspend fun getRepositoryByName(name: String) : Response<GithubModel> {
        return RetrofitInstance.api.getRepositoryByName(name)
    }
}