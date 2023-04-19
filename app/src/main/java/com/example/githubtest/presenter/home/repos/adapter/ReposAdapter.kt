package com.example.githubtest.presenter.home.repos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtest.core.entity.ReposItem
import com.example.githubtest.core.extentions.displayCircleImage
import com.example.githubtext.R
import com.example.githubtext.databinding.CellReposBinding

class ReposAdapter(
    private val repos: List<ReposItem>,
    private val listener: OnFavoriteClickListener,
    private val checkFavorite:(Int,(Boolean)->Unit)->Unit ) :
    RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CellReposBinding.inflate(inflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repos[position]
        holder.bind(repo,checkFavorite)

    }

    override fun getItemCount(): Int {
        return repos.size
    }

    inner class RepoViewHolder(private val binding: CellReposBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var isFavorite = false

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(repo: ReposItem,checkFavorite:(Int,(Boolean)->Unit)->Unit) {
            binding.repo = repo
            binding.repoImage.displayCircleImage(repo.avatarUrl)
            checkFavorite(repo.id){
                if (it) {
                    binding.favoriteIcon.setImageResource(R.mipmap.heart_like)
                } else {
                    binding.favoriteIcon.setImageResource(R.mipmap.heart_dislike)

                }
            }

            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onFavoriteClick(repos[position], binding.favoriteIcon)

            }
        }
    }

    interface OnFavoriteClickListener {
        fun onFavoriteClick(repo: ReposItem, favoriteIcon: ImageView)
    }
}