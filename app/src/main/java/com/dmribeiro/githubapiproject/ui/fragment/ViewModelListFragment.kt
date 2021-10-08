package com.dmribeiro.githubapiproject.ui.fragment

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dmribeiro.githubapiproject.data.repository.Repository
import com.dmribeiro.githubapiproject.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ViewModelListFragment @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getAllRepos(): Flow<PagingData<Repo>>{
        return repository.getSearchResultStream().cachedIn(viewModelScope)
    }
}