package com.example.frontend.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frontend.R

class CommentsAdapter(private val commentContents: Array<String>) :
    RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CommentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(commentContents[position])
    }

    override fun getItemCount(): Int {
        return commentContents.size
    }

    class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val commentContent: TextView

        init {
            commentContent = itemView.findViewById<TextView>(R.id.commentContent)
        }

        fun bind(comment: String?) {
            commentContent.text = comment
        }
    }
}