package vistula.mh.githubsearchapplication.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.repository_details_item.view.*
import vistula.mh.githubsearchapplication.R
import vistula.mh.githubsearchapplication.model.commitss.CommitModelItem


class CommitsAdapter(private val dataList: MutableList<List<CommitModelItem>>) : RecyclerView.Adapter<CommitsHolder>() {

    private lateinit var context: Context
    private var items: MutableList<List<CommitModelItem>> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsHolder {
        context=parent.context
        items = dataList
        return CommitsHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.repository_details_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return if (dataList.size > 3) {
            3
        } else {
            dataList.size
        }
    }

    override fun onBindViewHolder(holder: CommitsHolder, position: Int) {

        val list : CommitModelItem = dataList[position][position]
        val commitMessage = holder.itemView.commit_message_text_view_id
        val commitAuthorName = holder.itemView.commit_author_name_text_view_id
        val commitAuthorEmail = holder.itemView.commit_author_email_text_view_id

        commitMessage.text = list.commit.message
        commitAuthorName.text = list.commit.committer.name
        commitAuthorEmail.text = list.commit.committer.email
    }
}

class CommitsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private fun AdapterView.OnItemClickListener.onItemClick(result: CommitModelItem) {
        onItemClick(result)
    }
}