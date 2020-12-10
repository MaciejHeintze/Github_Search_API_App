package vistula.mh.githubsearchapplication.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import kotlinx.android.synthetic.main.repository_search_layout.*
import kotlinx.android.synthetic.main.repository_search_layout.view.*
import vistula.mh.githubsearchapplication.R
import vistula.mh.githubsearchapplication.TAG
import vistula.mh.githubsearchapplication.model.repository.GithubModel
import vistula.mh.githubsearchapplication.retrofit.MainViewModel
import vistula.mh.githubsearchapplication.retrofit.MainViewModelFactory
import vistula.mh.githubsearchapplication.retrofit.Repository
import vistula.mh.githubsearchapplication.retrofit.RepositoryAdapter

class SearchRepositoryFragment : Fragment(){

    private lateinit var repositoryNameSearchQuery: String
    private lateinit var viewModel: MainViewModel
    private var dataList: MutableList<GithubModel> = mutableListOf()
    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.repository_search_layout, container, false)
        val list = mutableListOf<GithubModel>()
        repositoryAdapter= RepositoryAdapter(list)
        view.recycler_view_id.layoutManager= LinearLayoutManager(context)
        view.recycler_view_id.addItemDecoration(DividerItemDecoration(context, OrientationHelper.VERTICAL))
        view.recycler_view_id.adapter=repositoryAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInputRepositoryName()


    }

    private fun setupRecyclerView(){
        repositoryAdapter= RepositoryAdapter(dataList)
        recycler_view_id.layoutManager= LinearLayoutManager(context)
        recycler_view_id.addItemDecoration(DividerItemDecoration(context, OrientationHelper.VERTICAL))
        recycler_view_id.adapter=repositoryAdapter
    }

    private fun getInputRepositoryName(){
        search_edit_text_id.addTextChangedListener(object : TextWatcher {

            var handler: Handler = Handler(Looper.getMainLooper())
            var workRunnable: Runnable? = null

            override fun afterTextChanged(mEdit: Editable) {
                if(mEdit.length > 3) {
                    workRunnable = Runnable { fetchDataByRepositoryName(mEdit.toString()) }
                    handler.postDelayed(workRunnable!!, 1000)
                }
            }
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
        })
    }


    private fun fetchDataByRepositoryName(mEdit: String){
        if(dataList.isNotEmpty()){
            dataList.clear()
            repositoryAdapter.notifyDataSetChanged()
        }
        repositoryNameSearchQuery = mEdit
        if(!repositoryNameSearchQuery.isNullOrEmpty()){
            fetchData(repositoryNameSearchQuery)
        }else{
            dataList.clear()
            repositoryAdapter.notifyDataSetChanged()
        }
    }

    private fun fetchData(repoName: String){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getRepositoryByName(repoName)
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful){
                response.body()?.let { dataList.add(it) }
                setupRecyclerView()
                repositoryAdapter.notifyDataSetChanged()
            }else{
                Log.d(TAG, response.message())
            }
        })
    }
}