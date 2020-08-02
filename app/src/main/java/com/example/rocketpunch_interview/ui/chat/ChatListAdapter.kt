package com.example.rocketpunch_interview.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketpunch_interview.R

import com.example.rocketpunch_interview.databinding.RowMyChatBinding
import com.example.rocketpunch_interview.databinding.RowOtherChatBinding
import com.example.rocketpunch_interview.model.Chat
import com.example.rocketpunch_interview.model.RowType


class ChatListViewHolder : RecyclerView.ViewHolder {
    internal lateinit var rowMyChatBinding: RowMyChatBinding
    internal lateinit var rowOtherChatBinding: RowOtherChatBinding


    constructor(rowMyChatBinding: RowMyChatBinding) : super(rowMyChatBinding.root) {
        this.rowMyChatBinding = rowMyChatBinding
    }

    constructor(rowOtherChatBinding: RowOtherChatBinding) : super(rowOtherChatBinding.root) {
        this.rowOtherChatBinding = rowOtherChatBinding
    }


}

class ChatListAdapter(var items: List<Chat> = arrayListOf(), var viewModel: ChatViewModel): RecyclerView.Adapter<ChatListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        return when (items[viewType].viewType.index) {
            RowType.MYCHAT.index -> ChatListViewHolder(DataBindingUtil.inflate<RowMyChatBinding>(LayoutInflater.from(parent.context), R.layout.row_my_chat, parent, false))
            RowType.OTHERCHAT.index -> ChatListViewHolder(DataBindingUtil.inflate<RowOtherChatBinding>(LayoutInflater.from(parent.context), R.layout.row_other_chat, parent, false))
            else -> ChatListViewHolder(DataBindingUtil.inflate<RowOtherChatBinding>(LayoutInflater.from(parent.context), R.layout.row_my_chat, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {

        when(items[position].viewType.index) {
            RowType.MYCHAT.index -> {
                holder.rowMyChatBinding.item = items[position]
                holder.rowMyChatBinding.viewModel = viewModel
            }
            RowType.OTHERCHAT.index -> {
                holder.rowOtherChatBinding.item = items[position]
                holder.rowOtherChatBinding.viewModel = viewModel
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}

@BindingAdapter(value = ["items", "viewModel"])
fun bindItem(view: RecyclerView, items: List<Chat>?, viewModel: ChatViewModel) {
    items?.let {
        view.adapter?.run {
            if (this is ChatListAdapter) {
                this.items = it
                this.notifyDataSetChanged()
            }
        } ?: run {
            ChatListAdapter(it, viewModel).apply {
                this.setHasStableIds(true)
                view.adapter = this
            }

        }
    }
}