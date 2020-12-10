package vistula.mh.githubsearchapplication.model.commits


import com.google.gson.annotations.SerializedName

data class CommitModelItem(
    var author: Author = Author(),
    @SerializedName("comments_url")
    var commentsUrl: String = "",
    var commit: Commit = Commit(),
    var committer: Committer = Committer(),
    @SerializedName("html_url")
    var htmlUrl: String = "",
    @SerializedName("node_id")
    var nodeId: String = "",
    var parents: List<Parent> = listOf(),
    var sha: String = "",
    var url: String = ""
)