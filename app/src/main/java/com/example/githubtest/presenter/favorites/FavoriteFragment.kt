package com.example.githubtest.presenter.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.githubtest.core.entity.Repos
import com.example.githubtest.core.entity.ReposItem
import com.example.githubtest.presenter.favorites.adapter.FavoriteAdapter
import com.example.githubtest.presenter.home.repos.adapter.ReposAdapter
import com.example.githubtext.R
import com.example.githubtext.databinding.FavoriteFragmentBinding
import com.example.githubtext.databinding.FragmentReposBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FavoriteFragment : Fragment() {

    private lateinit var adapter: FavoriteAdapter
    private var _binding: FavoriteFragmentBinding? = null

    val viewModel: FavoriteViewModel by viewModel()
    private val binding get() = _binding!!
    lateinit var favoriteIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        binding.reposList.layoutManager = LinearLayoutManager(context, VERTICAL, false)
        adapter =
            FavoriteAdapter(arrayListOf(), object : FavoriteAdapter.OnFavoriteClickListener {
                override fun onFavoriteClick(repo: ReposItem, favoriteIcon: ImageView) {
                    this@FavoriteFragment.favoriteIcon = favoriteIcon
                    viewModel.removeFroMFavorite(repo)


                }
            })
        binding.reposList.adapter = adapter
        viewModel.getAll()
        binding.backIcon.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun observe() {


        viewModel.allReposLiveData.observe(viewLifecycleOwner) {
            adapter.repos.clear()
            adapter.repos.addAll(it)
            adapter.notifyDataSetChanged()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}