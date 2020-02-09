package com.makestorming.mapdatacontrol.api

import com.google.gson.annotations.SerializedName
import com.makestorming.mapdatacontrol.model.Repo

data class RepoSearchResponse(

    @SerializedName("total_count") val itemCount : String,
    @SerializedName("items") val items : List<Repo> = emptyList(),
    val nextPage: Int? = null

)