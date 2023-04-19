package com.example.githubtest.presenter.home.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.githubtest.core.entity.Repos
import com.example.githubtest.core.entity.ReposItem
import com.example.githubtest.presenter.home.repos.adapter.ReposAdapter
import com.example.githubtext.R
import com.example.githubtext.databinding.FragmentReposBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ReposFragment : Fragment() {

    private var _binding: FragmentReposBinding? = null

    val viewModel: ReposViewModel by viewModel()
    private val binding get() = _binding!!
    lateinit var favoriteIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentReposBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//


        observe()
        viewModel.getRepos()

    }

    fun observe() {

        viewModel.reposLiveData.observe(viewLifecycleOwner) {
            viewModel.repos = it

            setup(it)
        }

        viewModel.addOrRemoveLiveData.observe(viewLifecycleOwner) {
            if (it) {
                favoriteIcon.setImageResource(R.mipmap.heart_like)
            } else {
                favoriteIcon.setImageResource(R.mipmap.heart_dislike)

            }

        }

    }

    fun setup(repos: Repos) {

        binding.reposList.layoutManager = LinearLayoutManager(context, VERTICAL, false)
        val adapter = ReposAdapter(repos, object : ReposAdapter.OnFavoriteClickListener {
            override fun onFavoriteClick(repo: ReposItem, favoriteIcon: ImageView) {
                this@ReposFragment.favoriteIcon = favoriteIcon


                viewModel.addOrRemoveFromDataBase(repo)
            }
        }){id,isFavoriteAct->
            viewModel.getRepoById(id,isFavoriteAct)
        }
        binding.reposList.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}