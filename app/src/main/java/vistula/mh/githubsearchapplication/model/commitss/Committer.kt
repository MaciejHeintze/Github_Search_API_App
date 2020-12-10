package vistula.mh.githubsearchapplication.model.commitss


import com.google.gson.annotations.SerializedName

data class Committer(
    var date: String = "",
    var email: String = "",
    var name: String = ""
)