package vistula.mh.githubsearchapplication.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import vistula.mh.githubsearchapplication.model.commits.CommitModelItem
import vistula.mh.githubsearchapplication.model.repository.GithubModel

interface Api{

    @GET("search/repositories")
    suspend fun getRepositoryByName(@Query("q") q: String) : Response<GithubModel>

    @GET("repos/{login}/{name}/commits")
    suspend fun getLastCommits(@Path("login") full_name: String, @Path("name") name : String) : Response<List<CommitModelItem>>
}