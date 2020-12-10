package vistula.mh.githubsearchapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_repository_details.view.*
import vistula.mh.githubsearchapplication.R
import vistula.mh.githubsearchapplication.retrofit.AVATAR_ID
import vistula.mh.githubsearchapplication.retrofit.LOGIN_ID
import vistula.mh.githubsearchapplication.retrofit.STARS_ID

class RepositoryDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repository_details, container, false)
        initValues(view)
        onBackButtonPressed(view)
        return view
    }

    private fun onBackButtonPressed(view: View){
        view.back_button_id.setOnClickListener {
            findNavController(this).setGraph(R.navigation.repository_graph)
        }
    }

    private fun initValues(view: View){
        val avatar = arguments?.getString(AVATAR_ID)
        val login = arguments?.getString(LOGIN_ID)
        val stars = arguments?.getString(STARS_ID)

        Glide.with(this)
            .load(avatar)
            .into(view.repository_avatar_id)
        view.number_of_stars_details_text_view_id.text = "Number of stars ($stars)"
        view.repository_author_name_id.text = login
    }
}