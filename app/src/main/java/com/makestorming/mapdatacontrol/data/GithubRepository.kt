package com.makestorming.mapdatacontrol.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.makestorming.mapdatacontrol.api.GithubService
import com.makestorming.mapdatacontrol.api.searchRepos
import com.makestorming.mapdatacontrol.db.GithubLocalCache
import com.makestorming.mapdatacontrol.model.RepoSearchResult

class GithubRepository (context: Context){

    private val service: GithubService = GithubService.create()
    private val cache: GithubLocalCache = GithubLocalCache(context)
//    private val cache: Object = Object()

    private var lastRequestedPage = 1

    private val networkErrors = MutableLiveData<String>()

    private var isRequestInProgress = false

    fun search(query: String): RepoSearchResult{
        Log.d("GithubRepository", "New query: $query")
        lastRequestedPage = 1
        requestAndSaveData(query)

        val data = cache.reposByName(query)

        return RepoSearchResult(data, networkErrors)

    }

    fun requestMore(query: String){
        requestAndSaveData(query)
    }

    private fun requestAndSaveData(query: String){
        if(isRequestInProgress) return

        isRequestInProgress = true
        searchRepos(service, query, lastRequestedPage, NETWORK_PAGE_SIZE, { repos ->
            cache.insert(repos) {
                lastRequestedPage++
                isRequestInProgress = false
            }
        }, {
            error ->
            networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

}