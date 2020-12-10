package vistula.mh.githubsearchapplication.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_repository_details.*
import kotlinx.android.synthetic.main.fragment_repository_details.view.*
import vistula.mh.githubsearchapplication.R
import vistula.mh.githubsearchapplication.TAG
import vistula.mh.githubsearchapplication.model.commits.CommitModelItem
import vistula.mh.githubsearchapplication.retrofit.*


class RepositoryDetailsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var dataList: MutableList<List<CommitModelItem>> = mutableListOf()
    private lateinit var commitsAdapter: CommitsAdapter
    private lateinit var login: String
    private lateinit var name: String
    private lateinit var repositoryUrl : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repository_details, container, false)
        initValues(view)
        onBackButtonPressed(view)
        fetchData(login,name)
        onShareButtonPressed(view)
        return view
    }

    private fun onShareButtonPressed(view: View) {
        view.share_repo_button_id.setOnClickListener {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "text/plain"
        i.putExtra(Intent.EXTRA_SUBJECT, name)
        i.putExtra(Intent.EXTRA_TEXT, repositoryUrl)
        startActivity(Intent.createChooser(i, "Share repository URL"))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData(login,name)
    }

    private fun onBackButtonPressed(view: View){
        view.back_button_id.setOnClickListener {
            findNavController(this).setGraph(R.navigation.repository_graph)
        }
    }

    private fun initValues(view: View){
        val avatar = arguments?.getString(AVATAR_ID)
        login = arguments?.getString(LOGIN_ID).toString()
        val stars = arguments?.getString(STARS_ID)
        name = arguments?.getString(NAME_ID).toString()
        repositoryUrl = arguments?.getString(URL_ID).toString()

        Glide.with(this)
            .load(avatar)
            .into(view.repository_avatar_id)

        view.number_of_stars_details_text_view_id.text = "Number of stars ($stars)"
        view.repository_author_name_id.text = login
    }

    private fun fetchData(login: String, name: String){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getLastCommits(login, name)
        viewModel.commitResponse.observe(this as LifecycleOwner, Observer { response ->
            if(response.isSuccessful){
                response.body()?.let { dataList.add(it) }
                setupRecyclerView()
                commitsAdapter.notifyDataSetChanged()
            }else{
                Log.d(TAG, response.message())
            }
        })
    }

    private fun setupRecyclerView(){
        commitsAdapter = CommitsAdapter(dataList)
        commits_recycler_view_id.layoutManager= LinearLayoutManager(context)
        commits_recycler_view_id.addItemDecoration(DividerItemDecoration(context, OrientationHelper.VERTICAL))
        commits_recycler_view_id.adapter=commitsAdapter
    }
}