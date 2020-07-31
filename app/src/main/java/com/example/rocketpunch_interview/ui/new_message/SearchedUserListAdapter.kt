package com.example.rocketpunch_interview.ui.new_message

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketpunch_interview.R
import com.example.rocketpunch_interview.databinding.RowUserBinding
import com.example.rocketpunch_interview.model.User


class UserViewHolder(val binding: RowUserBinding) : RecyclerView.ViewHolder(binding.root)

class SearchedUserListAdapter(var items: List<User> = arrayListOf(), var viewModel: NewMessageViewModel): RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_user,
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.item = items[position]
        holder.binding.viewModel = viewModel
    }

}

@BindingAdapter(value = ["items", "viewModel"])
fun bindItem(view: RecyclerView, items: List<User>?, viewModel: NewMessageViewModel) {
    items?.let {
        view.adapter?.run {
            if (this is SearchedUserListAdapter) {
                this.items = it
                this.notifyDataSetChanged()
            }
        } ?: run {
            SearchedUserListAdapter(it, viewModel).apply {
                this.setHasStableIds(true)
                view.adapter = this
            }

        }
    }
}