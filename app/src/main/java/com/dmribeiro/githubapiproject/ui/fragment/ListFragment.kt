package com.dmribeiro.githubapiproject.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dmribeiro.githubapiproject.data.remote.NetworkResource
import com.dmribeiro.githubapiproject.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ViewModelListFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        viewModel = ViewModelProvider(requireActivity()).get(ViewModelListFragment::class.java)
        binding = FragmentListBinding.inflate(layoutInflater, container, false)


        requestApiData()
        return binding.root
    }

    private fun requestApiData(){
        viewModel.getAllReposByStars()
        viewModel.repoResponse.observe(viewLifecycleOwner, { response ->
            when(response){
                is NetworkResource.Success ->{
                    response.data?.let {
                        Log.d("***Success", it.items.toString())
                    }
                }
                is NetworkResource.Error ->{
                    Log.d("***Error", response.message.toString())
                }
            }
        })
    }
}