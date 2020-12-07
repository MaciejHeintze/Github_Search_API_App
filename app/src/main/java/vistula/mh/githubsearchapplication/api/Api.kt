package vistula.mh.githubsearchapplication.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import vistula.mh.githubsearchapplication.model.GithubModel

interface Api{

    @GET("search/repositories")
    suspend fun getRepositoryByName(@Query("q") q: String) : Response<GithubModel>

}