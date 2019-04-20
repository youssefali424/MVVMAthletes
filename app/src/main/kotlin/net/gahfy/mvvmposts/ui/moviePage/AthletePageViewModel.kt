package net.gahfy.mvvmposts.ui.moviePage

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import net.gahfy.mvvmposts.model.Athlete

class AthletePageViewModel : ViewModel() {

    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()
    private val postImageUrl = MutableLiveData<String>()
    fun bind(athlete: Athlete){
        postTitle.value = athlete.name
        postBody.value = athlete.brief
        postImageUrl.value = athlete.image
    }

    fun getPostTitle():MutableLiveData<String>{
        return postTitle
    }
    fun getImageUrl():MutableLiveData<String>{
        return postImageUrl
    }
    fun getPostBody():MutableLiveData<String>{
        return postBody
    }
}