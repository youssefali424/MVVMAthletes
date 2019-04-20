package net.gahfy.mvvmposts.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable


@Entity
data class Athlete (

        val name : String,
        val image : String,
        val brief : String
): Serializable{
        @PrimaryKey(autoGenerate = true)
        var atheleteId: Int = 0
}