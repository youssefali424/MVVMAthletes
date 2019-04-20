package net.gahfy.mvvmposts.ui.post

import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.gahfy.mvvmposts.R
import net.gahfy.mvvmposts.base.BaseViewModel
import net.gahfy.mvvmposts.model.Athlete
import net.gahfy.mvvmposts.model.PostDao
import net.gahfy.mvvmposts.network.PostApi
import net.gahfy.mvvmposts.ui.moviePage.AthletePageActivity
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao):BaseViewModel(){
    @Inject
    lateinit var postApi: PostApi
    val postListAdapter: PostListAdapter = PostListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable

    init{
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPosts(){

        var lists=postApi.getPosts().subscribe()
        subscription = Observable.fromCallable { postDao.all }
                .concatMap { dbPostList ->
                        if(dbPostList.isEmpty()){
                            Log.i("here"," reached")
                            postApi.getPosts().map {p->p.athletes}.concatMap {
                                            apiPostList -> postDao.insertAll(*apiPostList.toTypedArray())
                                Observable.just(apiPostList)
                                       }
                        }
                        else
                            Observable.just(dbPostList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) },
                        { onRetrievePostListError() }
                )
    }
    fun loadMore(){

    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList:List<Athlete>){
        postListAdapter.updatePostList(postList)
        postListAdapter.setOnItemClickListener(object : PostListAdapter.OnItemClickListener {
            override fun onClick(view: View, data: Athlete) {
                Toast.makeText(view.context, data.name, Toast.LENGTH_LONG).show()
                val intent = Intent(view.context, AthletePageActivity::class.java)
                intent.putExtra("nowMovie", data)
                view.context.startActivity(intent)
            }
        })
    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error
    }
}