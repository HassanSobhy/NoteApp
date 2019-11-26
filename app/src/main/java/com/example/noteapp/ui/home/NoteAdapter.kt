package com.example.noteapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.database.Note

class NoteAdapter : ListAdapter<Note,NoteAdapter.ViewHolder>(NoteDiffCallBack()) {

    /*var data = listOf<Note>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)
    }

   /* override fun getItemCount() = data.size*/

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.note_item_title_text)
        val desc: TextView = itemView.findViewById(R.id.note_item_desc_text)


        fun bind(item: Note) {
            title.text = item.title
            desc.text = item.desc
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
                return ViewHolder(view)
            }
        }
    }


}

class NoteDiffCallBack : DiffUtil.ItemCallback<Note>()
{
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.noteId == newItem.noteId
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}