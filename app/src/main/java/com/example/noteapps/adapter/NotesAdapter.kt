package com.example.noteapps.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapps.model.Note
import kotlin.random.Random

class NotesAdapter(private val context: Context, val listener: NotesClickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private  val NotesList = ArrayList<Note>()
    private  val fullList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(com.example.noteapps.R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = NotesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true
        holder.note.text = currentNote.note
        holder.date.text = currentNote.date
        holder.note.isSelected = true

        holder.notes_layout.setBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))

        holder.notes_layout.setOnClickListener{
            listener.onItemClicked(NotesList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener{
            listener.onLongItemClicked(NotesList[holder.adapterPosition], holder.notes_layout)
            true
        }

    }

    override fun getItemCount(): Int {
        return NotesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>){
        fullList.clear()
        fullList.addAll(newList)

        NotesList.clear()
        NotesList.addAll(fullList)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(search: String){
        NotesList.clear()
        for(item in fullList){
            if (
                item.title?.lowercase()?.contains(search.lowercase()) == true ||
                item.note?.lowercase()?.contains(search.lowercase()) == true
                ){
                NotesList.add(item)
            }
        }

        notifyDataSetChanged()

    }

    fun randomColor() : Int {
        val list = ArrayList<Int>()
        list.add(com.example.noteapps.R.color.NoteColor1)
        list.add(com.example.noteapps.R.color.NoteColor2)
        list.add(com.example.noteapps.R.color.NoteColor3)
        list.add(com.example.noteapps.R.color.NoteColor4)
        list.add(com.example.noteapps.R.color.NoteColor5)
        list.add(com.example.noteapps.R.color.NoteColor6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]

    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notes_layout = itemView.findViewById<CardView>(com.example.noteapps.R.id.card_layout)
        val title = itemView.findViewById<TextView>(com.example.noteapps.R.id.tvTitle)
        val note = itemView.findViewById<TextView>(com.example.noteapps.R.id.tvNotes)
        val date = itemView.findViewById<TextView>(com.example.noteapps.R.id.tvDate)

    }

    interface NotesClickListener{
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)
    }

}

//        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return NoteViewHolder(binding)

//        fun bind(note: Note){
//            binding.tvTitle.text = note.title
//            binding.tvNotes.text = note.note
//            binding.tvDate.text = note.date
//        }