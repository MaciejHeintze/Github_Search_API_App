package vistula.mh.githubsearchapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vistula.mh.githubsearchapplication.ui.SearchRepositoryFragment

const val TAG = "RETROFIT_API_CALL"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_search_layout)

        if (savedInstanceState == null) {
            val fragment = SearchRepositoryFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_content, fragment)
                .commit()
        }
    }
}