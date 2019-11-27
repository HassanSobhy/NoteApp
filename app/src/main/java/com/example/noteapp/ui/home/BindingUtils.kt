package com.example.noteapp.ui.home

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.noteapp.database.Note


@BindingAdapter("setTitle")
fun TextView.setTitle(item: Note) {
    item?.let {
        text = item.title
    }
}

@BindingAdapter("setDescription")
fun TextView.setDesc(item:Note)
{
    item?.let {
        text = item.desc
    }
}