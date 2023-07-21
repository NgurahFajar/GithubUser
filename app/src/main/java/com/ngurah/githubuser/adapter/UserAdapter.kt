package com.ngurah.githubuser.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngurah.githubuser.R
import com.ngurah.githubuser.api.ItemsItem
import com.ngurah.githubuser.databinding.ItemViewBinding
import com.ngurah.githubuser.room.UserEntity
import kotlin.contracts.contract

class UserAdapter(private val isFavoritesActivity: Boolean = false, private val onDeleteClicked: (UserEntity) -> Unit = {}) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val listUser = ArrayList<ItemsItem>()
    private lateinit var onItemClickCallBack: OnItemClickCallBack


    @SuppressLint("NotifyDataSetChanged")
    fun setListUser(users: ArrayList<ItemsItem>) {
        listUser.apply {
            clear()
            addAll(users)
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    inner class ViewHolder(var binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem) {
            binding.root.setOnClickListener {
                onItemClickCallBack?.OnItemClick(user)
            }

            Glide.with(itemView)
                .load(user.avatarUrl)
                .into(binding.image)

            binding.username.text = user.login
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemViewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val (usernames, avatarUrl, name) = listUser[position]
        val userEntity = usernames?.let { UserEntity(it, avatarUrl, name.toString()) }

        viewHolder.binding.apply {
            image.setImage(viewHolder.itemView.context, avatarUrl)
            if (usernames == null){
                username.text = usernames
            } else{
                username.text = name.toString()
            }
            btnDelete.visibility = if (isFavoritesActivity) View.VISIBLE else View.GONE
            btnDelete.setOnClickListener {
                if (userEntity != null) {
                    onDeleteClicked(userEntity)
                }
            }

        }
        viewHolder.itemView.setOnClickListener {onItemClickCallBack.OnItemClick(listUser[viewHolder.adapterPosition]) }
    }

    interface OnItemClickCallBack {
        fun OnItemClick(data: ItemsItem)
    }

    fun ImageView.setImage(context: Context, resourceId: String?) {
        Glide.with(context).load(resourceId).circleCrop()
            .placeholder(R.drawable.blank_image).circleCrop()
            .into(this)
    }

}
