package net.gahfy.mvvmposts.ui.post

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.gahfy.mvvmposts.R
import net.gahfy.mvvmposts.databinding.ItemPostBinding
import net.gahfy.mvvmposts.databinding.ItemPostNoimageBinding
import net.gahfy.mvvmposts.model.Athlete

class PostListAdapter: RecyclerView.Adapter<PostListAdapter.BaseViewHolder<*>>() {
    private lateinit var postList:List<Athlete>
    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  BaseViewHolder<*> {
        setOnItemClickListener(listener)
        val binding: ItemPostBinding
        val bindingNoImage : ItemPostNoimageBinding
        if(viewType==1){
            bindingNoImage = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post_noimage, parent, false)
            return ViewHolderNoImage(bindingNoImage)
        }
        else {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
            return ViewHolder(binding)
        }

    }
    override fun getItemViewType(position: Int): Int {
        val comparable = postList[position]
        return if (comparable.image.equals("")) 1 else 2
    }
    override fun onBindViewHolder(holder: PostListAdapter. BaseViewHolder<*>, position: Int) {
        val ele=postList[position]
        holder.bind(ele,listener)

    }

    override fun getItemCount(): Int {
        return if(::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList:List<Athlete>){
        this.postList = postList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClick(view: View, data: Athlete)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    abstract class BaseViewHolder<in T> (itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bind(post: Athlete,listener: OnItemClickListener)
    }
    class ViewHolder( val binding: ItemPostBinding):BaseViewHolder<Athlete>(binding.root){
        private val viewModel = PostViewModel()

        override fun bind(post:Athlete,listener: OnItemClickListener){
            viewModel.bind(post)
            binding.viewModel = viewModel
            binding.rowLayout.setOnClickListener {
                listener.onClick(it, post)
            }
        }
    }
    class ViewHolderNoImage( val binding: ItemPostNoimageBinding):BaseViewHolder<Athlete>(binding.root){
        private val viewModel = PostViewModel()

        override fun bind(post:Athlete,listener: OnItemClickListener){
            viewModel.bind(post)
            binding.viewModel = viewModel
            binding.rowLayout.setOnClickListener {
                listener.onClick(it, post)
            }
        }
    }
}