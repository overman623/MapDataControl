package com.makestorming.mapdatacontrol.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makestorming.mapdatacontrol.model.Repo

@Dao
interface RepoDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Repo>)

    @Query("SELECT * FROM repos WHERE (name LIKE : queryString) OR (description LIKE " +
            ":queryString) ORDER BY starts DESC, name ASC)")
    fun reposByName(queryString: String): LiveData<List<Repo>>

}