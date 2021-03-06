package vistula.mh.githubsearchapplication.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.repository_item.view.*
import vistula.mh.githubsearchapplication.R
import vistula.mh.githubsearchapplication.model.repository.GithubModel

const val AVATAR_ID = "avatar"
const val LOGIN_ID = "login"
const val STARS_ID = "stars"
const val NAME_ID = "name"
const val URL_ID = "url"

class RepositoryAdapter(private val dataList: MutableList<GithubModel>) : RecyclerView.Adapter<RepositoryHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {
        context=parent.context
        return RepositoryHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.repository_item,parent,false)
        )
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        val data = dataList[position]

        val icon = holder.itemView.repository_item_image_view_id
        val repositoryName = holder.itemView.repository_name_id
        val numberOfStars = holder.itemView.number_of_stars_text_view_id
        repositoryName.text = data.items[position].name
        numberOfStars.text = data.items[position].stargazersCount.toString()
        val stars = data.items[position].stargazersCount.toString()
        val repositoryIcon = data.items[position].owner.avatarUrl
        val login = data.items[position].owner.login
        val name = data.items[position].name
        val repositoryUrl = data.items[position].htmlUrl

        Glide.with(context)
            .load(repositoryIcon)
            .into(icon)

        holder.itemView.setOnClickListener {
            val bundle = bundleOf(
                AVATAR_ID to repositoryIcon,
                STARS_ID to stars,
                LOGIN_ID to login,
                NAME_ID to name,
                URL_ID to repositoryUrl
            )
            it.findNavController().navigate(R.id.action_searchRepositoryFragment_to_repositoryDetailsFragment, bundle)
        }
    }
}

class RepositoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private fun AdapterView.OnItemClickListener.onItemClick(result: GithubModel) {
    onItemClick(result)
}