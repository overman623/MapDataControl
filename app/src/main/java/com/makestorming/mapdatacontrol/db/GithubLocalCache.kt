package com.makestorming.mapdatacontrol.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.makestorming.mapdatacontrol.model.Repo
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GithubLocalCache (context: Context){

    private val repoDao: RepoDao = RepoDatabase.getInstance(context).reposDao()
    private val ioExecutor: Executor = Executors.newFixedThreadPool(4)

    fun insert(repos: List<Repo>, insertFinished:() -> Unit){
        ioExecutor.execute{
            Log.d("GithubLocalCache", "inserting ${repos.size} repos")
            repoDao.insert(repos)
            insertFinished()
        }
    }

    fun reposByName(name: String): LiveData<List<Repo>> {
        // appending '%' so we can allow other characters to be before and after the query string
        val query = "%${name.replace(' ', '%')}%"
        return repoDao.reposByName(query)
    }

}