package com.makestorming.mapdatacontrol.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.makestorming.mapdatacontrol.data.GithubRepository
import com.makestorming.mapdatacontrol.model.Repo
import com.makestorming.mapdatacontrol.model.RepoSearchResult

class SearchRepositoriesViewModel (application: Application) : AndroidViewModel(application){

    companion object{
        private const val VISIBLE_THRESHOLD = 5
    }

    private val repository: GithubRepository = GithubRepository(application)

    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<RepoSearchResult> = Transformations.map(queryLiveData) {
        repository.search(it)
    }

    val repos: LiveData<List<Repo>> = Transformations.switchMap(repoResult) {it.data}
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) {it.networkErrors}


    fun searchRepo(queryString: String){
        queryLiveData.postValue(queryString)
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int){
        if(visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount){
            val immutableQuery = lastQueryValue()
            if(immutableQuery != null){
                repository.requestMore(immutableQuery)
            }
        }
    }

    fun lastQueryValue(): String? = queryLiveData.value

}