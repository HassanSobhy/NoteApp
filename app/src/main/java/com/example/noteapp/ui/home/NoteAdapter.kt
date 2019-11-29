package com.example.noteapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.database.Note
import com.example.noteapp.databinding.NoteItemBinding

class NoteAdapter(val clickListener: NoteListener) : ListAdapter<Note,NoteAdapter.ViewHolder>(NoteDiffCallBack()) {

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
        holder.bind(item,clickListener)
    }

    class ViewHolder private constructor(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Note,
            clickListener: NoteListener
        ) {
            binding.note = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NoteItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
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

class NoteListener(val clickListener : (noteId : Long) -> Unit){
    fun onClick(note : Note)
    {
        return clickListener(note.noteId)
    }
}