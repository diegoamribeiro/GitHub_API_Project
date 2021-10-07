package com.dmribeiro.githubapiproject.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dmribeiro.githubapiproject.R
import com.dmribeiro.githubapiproject.databinding.ItemListBinding
import com.dmribeiro.githubapiproject.model.Repo
import java.text.NumberFormat

class RepoAdapter : PagingDataAdapter<Repo, RepoAdapter.RepoViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(repoItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem
        }
    }

    class RepoViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        private val tvRepoName = binding.tvRepo
        private val tvAuthor = binding.tvAuthor
        private val tvStar = binding.tvStar
        private val tvFork = binding.tvFork
        private val ivProfile = binding.ivProfile

        private var repo: Repo? = null

        fun bind(repo: Repo?) {
            if (repo == null) {
                val resources = itemView.resources
                tvRepoName.text = resources.getString(R.string.loading)
                tvAuthor.visibility = View.GONE
                ivProfile.visibility = View.GONE
                tvStar.text = resources.getString(R.string.unknown)
                tvFork.text = resources.getString(R.string.unknown)
            } else {
                showRepoData(repo)
            }
        }

        private fun showRepoData(repo: Repo) {
            this.repo = repo
            tvRepoName.text = repo.name
            tvAuthor.text = repo.owner.login
            tvStar.text = repo.stargazersCount.toString()
            tvFork.text = repo.forksCount.toString()
            Glide.with(ivProfile)
                .load(repo.owner.avatarUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivProfile)
        }

        companion object {
            fun create(parent: ViewGroup): RepoViewHolder {
                val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RepoViewHolder(binding)
            }
        }
    }
}