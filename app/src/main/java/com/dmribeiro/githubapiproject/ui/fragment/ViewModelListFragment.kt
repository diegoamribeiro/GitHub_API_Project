package com.dmribeiro.githubapiproject.ui.fragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dmribeiro.githubapiproject.data.remote.NetworkResource
import com.dmribeiro.githubapiproject.data.repository.Repository
import com.dmribeiro.githubapiproject.model.Repos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelListFragment @Inject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {

    val repoResponse: MutableLiveData<NetworkResource<Repos>> = MutableLiveData()


    fun getAllReposByStars() = viewModelScope.launch(Dispatchers.IO) {
        getAllRepos()
    }

    private suspend fun getAllRepos(){
        repoResponse.postValue(NetworkResource.Loading())
        val response = repository.getAllReposTest()
        Log.d("***", response.toString())
        repoResponse.postValue(handleRepoResponse(response))
    }

    private fun handleRepoResponse(response: Response<Repos>): NetworkResource<Repos>{
        repoResponse.postValue(NetworkResource.Loading())
        return if (response.isSuccessful){
            val repos = response.body()
            NetworkResource.Success(repos!!)
        }else{
            NetworkResource.Error(response.message())
        }
    }

}