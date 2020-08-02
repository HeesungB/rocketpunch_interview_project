package com.example.rocketpunch_interview.ui.channel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketpunch_interview.R
import com.example.rocketpunch_interview.databinding.RowChannelBinding
import com.example.rocketpunch_interview.model.Channel
import com.example.rocketpunch_interview.model.MessageChannel


class ChannelItemViewHolder(val binding: RowChannelBinding) : RecyclerView.ViewHolder(binding.root)

class ChannelListAdapter(var items: List<Channel> = arrayListOf(), var viewModel: ChannelViewModel): RecyclerView.Adapter<ChannelItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelItemViewHolder {
        return ChannelItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_channel,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChannelItemViewHolder, position: Int) {
        holder.binding.item = items[position]
        holder.binding.viewModel = viewModel
    }

}

@BindingAdapter(value = ["items", "viewModel"])
fun bindItem(view: RecyclerView, items: List<Channel>?, viewModel: ChannelViewModel) {
    items?.let {
        view.adapter?.run {
            if (this is ChannelListAdapter) {
                this.items = it
                this.notifyDataSetChanged()
            }
        } ?: run {
            ChannelListAdapter(it, viewModel).apply {
                this.setHasStableIds(true)
                view.adapter = this
            }

        }
    }
}
