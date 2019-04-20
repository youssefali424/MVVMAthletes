package net.gahfy.mvvmposts.ui.post

import android.arch.lifecycle.MutableLiveData
import net.gahfy.mvvmposts.base.BaseViewModel
import net.gahfy.mvvmposts.model.Athlete


class PostViewModel:BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()
    private val postImageUrl = MutableLiveData<String>()
    fun bind(athlete: Athlete){
        postTitle.value = athlete.name
        postBody.value = ""+athlete.brief
        postImageUrl.value=athlete.image
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