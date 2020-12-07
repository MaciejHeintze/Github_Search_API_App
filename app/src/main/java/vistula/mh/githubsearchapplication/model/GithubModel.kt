package vistula.mh.githubsearchapplication.model

import com.google.gson.annotations.SerializedName

data class GithubModel(
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean = false,
    var items: List<Item> = listOf(),
    @SerializedName("total_count")
    var totalCount: Int = 0
)