package vistula.mh.githubsearchapplication.model.commitss


import com.google.gson.annotations.SerializedName

data class Verification(
    var payload: String = "",
    var reason: String = "",
    var signature: String = "",
    var verified: Boolean = false
)