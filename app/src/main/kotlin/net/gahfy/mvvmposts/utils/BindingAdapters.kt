package net.gahfy.mvvmposts.utils

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import net.gahfy.mvvmposts.utils.extension.getParentActivity

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View,  visibility: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView,  text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}
@BindingAdapter("mutableImage")
fun setMutableImage(view: ImageView, url: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && url != null) {
        val circularProgressDrawable = CircularProgressDrawable(parentActivity)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
//        var str:String = IMG1_BASE_URL+url+IMG2_BASE_URL
//        Log.i("here",str)
        url.observe(parentActivity, Observer { value ->
            if(value==null||value.equals("")){
                view.visibility=View.INVISIBLE
            }
            else {
                Glide.with(parentActivity)
                        .load(value)
                        .apply(RequestOptions.placeholderOf(circularProgressDrawable))
                        .apply(RequestOptions.circleCropTransform())
                        .into(view)
            }
             //Log.i("here",str)
        })

    }
    else if(url==null||url.equals("")){
        view.visibility=View.INVISIBLE
    }
}
@BindingAdapter("mutableImageFull")
fun setMutableImageFull(view: ImageView, url: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && url != null) {
        val circularProgressDrawable = CircularProgressDrawable(parentActivity)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
//        var str:String = IMG1_BASE_URL+url+IMG2_BASE_URL
//        Log.i("here",str)
        url.observe(parentActivity, Observer { value ->
            if(value==null||value.equals("")){
                view.visibility = View.INVISIBLE
            }
            else {
                Glide.with(parentActivity)
                        .load(value)
                        .apply(RequestOptions.placeholderOf(circularProgressDrawable))
                        .apply(RequestOptions.centerCropTransform())
                        .into(view)
                Log.i("here",value)
            }
        })

    }

}