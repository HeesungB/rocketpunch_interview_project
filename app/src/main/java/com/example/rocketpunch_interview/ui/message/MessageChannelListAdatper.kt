package com.example.rocketpunch_interview.ui.message

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketpunch_interview.R
import com.example.rocketpunch_interview.databinding.RowMessageChannelBinding
import com.example.rocketpunch_interview.model.MessageChannel


class MessageChannelItemViewHolder(val binding: RowMessageChannelBinding) : RecyclerView.ViewHolder(binding.root)

class MessageChannelListAdapter(var items: List<MessageChannel> = arrayListOf(), var viewModel: MessageViewModel): RecyclerView.Adapter<MessageChannelItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageChannelItemViewHolder {
        return MessageChannelItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_message_channel,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MessageChannelItemViewHolder, position: Int) {
        Log.d("zz","onBindViewHolder")

        holder.binding.item = items[position]
        holder.binding.viewModel = viewModel
    }

}

@BindingAdapter(value = ["items", "viewModel"])
fun bindItem(view: RecyclerView, items: List<MessageChannel>?, viewModel: MessageViewModel) {

    items?.let {
        view.adapter?.run {
            Log.d("zz","yes adapter")
            if (this is MessageChannelListAdapter) {
                this.items = it
                this.notifyDataSetChanged()
            }
        } ?: run {
            Log.d("zz","no adapter")
            MessageChannelListAdapter(it, viewModel).apply {
                this.setHasStableIds(true)
                view.adapter = this
            }

        }
    }
}
