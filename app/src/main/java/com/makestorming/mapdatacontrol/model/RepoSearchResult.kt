package com.makestorming.mapdatacontrol.model

import androidx.lifecycle.LiveData

data class RepoSearchResult(
    val data: LiveData<List<Repo>>,
    val networkErrors: LiveData<String>
)