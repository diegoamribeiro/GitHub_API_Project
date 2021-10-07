package com.dmribeiro.githubapiproject.ui.fragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dmribeiro.githubapiproject.data.remote.NetworkResource
import com.dmribeiro.githubapiproject.data.repository.Repository
import com.dmribeiro.githubapiproject.model.Repo
import com.dmribeiro.githubapiproject.model.RepoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelListFragment @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    //val repoResponse: MutableLiveData<NetworkResource<RepoResponse>> = MutableLiveData()


    fun getAllRepos(): Flow<PagingData<Repo>>{
        return repository.getSearchResultStream().cachedIn(viewModelScope)
    }

}