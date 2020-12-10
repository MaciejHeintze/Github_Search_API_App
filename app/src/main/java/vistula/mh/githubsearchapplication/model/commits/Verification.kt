package vistula.mh.githubsearchapplication.model.commits


data class Verification(
    var payload: String = "",
    var reason: String = "",
    var signature: String = "",
    var verified: Boolean = false
)