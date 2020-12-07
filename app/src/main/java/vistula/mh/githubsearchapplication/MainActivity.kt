package vistula.mh.githubsearchapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import kotlinx.android.synthetic.main.activity_main.*
import vistula.mh.githubsearchapplication.model.GithubModel
import vistula.mh.githubsearchapplication.retrofit.MainViewModel
import vistula.mh.githubsearchapplication.retrofit.MainViewModelFactory
import vistula.mh.githubsearchapplication.retrofit.Repository
import vistula.mh.githubsearchapplication.retrofit.RepositoryAdapter

const val TAG = "RETROFIT_API_CALL"

class MainActivity : AppCompatActivity() {

    private lateinit var repositoryNameSearchQuery: String
    private lateinit var viewModel: MainViewModel
    private var dataList: MutableList<GithubModel> = mutableListOf()
    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        getInputRepositoryName()
    }

    private fun getInputRepositoryName(){
        search_edit_text_id.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(mEdit: Editable) {
                if(dataList.isNotEmpty()){
                    dataList.clear()
                }
                if(mEdit.length > 3) {
                    repositoryNameSearchQuery = mEdit.toString()
                    fetchData(repositoryNameSearchQuery)
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

    private fun setupRecyclerView(){
        repositoryAdapter= RepositoryAdapter(dataList)
        recycler_view_id.layoutManager= LinearLayoutManager(this)
        recycler_view_id.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        recycler_view_id.adapter=repositoryAdapter
    }

    private fun fetchData(repoName: String){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getRepositoryByName(repoName)
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful){
                response.body()?.let { dataList.add(it) }
                repositoryAdapter.notifyDataSetChanged()
            }else{
                Log.d(TAG, response.message())
            }
        })
    }
}