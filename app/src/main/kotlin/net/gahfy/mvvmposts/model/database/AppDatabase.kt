package net.gahfy.mvvmposts.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import net.gahfy.mvvmposts.model.Athlete
import net.gahfy.mvvmposts.model.PostDao

@Database(entities = [Athlete::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}