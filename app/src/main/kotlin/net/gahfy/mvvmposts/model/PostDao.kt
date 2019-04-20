package net.gahfy.mvvmposts.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PostDao {
    @get:Query("SELECT * FROM athlete")
    val all: List<Athlete>

    @Insert
    fun insertAll(vararg users: Athlete)
}