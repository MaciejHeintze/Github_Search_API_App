package vistula.mh.githubsearchapplication.model.commits


import com.google.gson.annotations.SerializedName

data class Parent(
    @SerializedName("html_url")
    var htmlUrl: String = "",
    var sha: String = "",
    var url: String = ""
)