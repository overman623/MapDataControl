package com.makestorming.mapdatacontrol.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.makestorming.mapdatacontrol.model.Repo

@Database(
    entities = [Repo::class],
    version = 1,
    exportSchema = false
)
abstract class RepoDatabase : RoomDatabase(){

    abstract fun reposDao(): RepoDao

    companion object{
        @Volatile
        private var INSTANCE: RepoDatabase? = null

        fun getInstance(context: Context): RepoDatabase{
            if(INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RepoDatabase::class.java, "word_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }

    }

}