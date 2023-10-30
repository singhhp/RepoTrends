package com.example.repotrends.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.repotrends.R
import com.example.repotrends.model.GitHubRepo

class GitHubRepoAdapter(public val data: MutableList<GitHubRepo>,private var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<GitHubRepoAdapter.ViewHolder>() {

    private val selectedItems = mutableSetOf<Int>()
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    fun setFilteredData(filteredData: List<GitHubRepo>) {
        data.clear()
        data.addAll(filteredData)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repoName: TextView = itemView.findViewById(R.id.repoName)
        val ownerName: TextView = itemView.findViewById(R.id.ownerName)

    }

    fun setSelectedItemPosition(position: Int) {
        selectedItemPosition = position
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = data[position]
        holder.repoName.text = repo.name
        holder.ownerName.text = repo.owner.login

        if (position == selectedItemPosition) {
            holder.itemView.setBackgroundResource(R.drawable.item_selector)
        } else {
            holder.itemView.setBackgroundResource(0)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position)
            toggleItemSelection(position)
            Log.d("GitHubRepoAdapter", "Selected Items: $selectedItems")
        }
        holder.itemView.isActivated = selectedItems.contains(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<GitHubRepo>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun toggleItemSelection(position: Int) {
        if (selectedItems.contains(position)) {
            selectedItems.remove(position)
        } else {
            selectedItems.add(position)
        }
        notifyItemChanged(position)
    }
}
