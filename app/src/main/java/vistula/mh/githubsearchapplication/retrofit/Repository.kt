package vistula.mh.githubsearchapplication.retrofit

import retrofit2.Response
import vistula.mh.githubsearchapplication.model.commits.CommitModelItem
import vistula.mh.githubsearchapplication.model.repository.GithubModel

class Repository {

    suspend fun getRepositoryByName(name: String) : Response<GithubModel> {
        return RetrofitInstance.api.getRepositoryByName(name)
    }

    suspend fun getLastCommits(login: String, name: String) : Response<List<CommitModelItem>> {
        return RetrofitInstance.api.getLastCommits(login, name)
    }
}