package com.dmribeiro.githubapiproject.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmribeiro.githubapiproject.databinding.FragmentListBinding
import com.dmribeiro.githubapiproject.ui.ReposLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import android.os.Parcelable
import androidx.core.view.get
import androidx.paging.RemoteMediator


@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel by viewModels<ViewModelListFragment>()
    private val adapter: RepoAdapter by lazy { RepoAdapter() }
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        setupRecyclerView()
        remoteCall()
        return binding.root
    }

    private fun setupRecyclerView(){
        recyclerView = binding.rvList
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter{adapter.retry()},
            footer = ReposLoadStateAdapter{adapter.retry()}
        )
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    private fun remoteCall(){
        lifecycleScope.launchWhenStarted {
            viewModel.getAllRepos().collectLatest { response ->
                adapter.submitData(response)
            }
        }
    }
}

