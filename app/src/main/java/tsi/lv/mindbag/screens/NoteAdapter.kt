package tsi.lv.mindbag.screens

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.note_item.view.*
import tsi.lv.mindbag.R
import tsi.lv.mindbag.domain.Note


class NoteAdapter(val ctx : Context, val notes: List<Note>, val listener : (Note) -> Unit) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false);
        return NoteViewHolder(view);
    }

    override fun onBindViewHolder(holderNote: NoteViewHolder, position: Int) {
        holderNote.bind(this.notes[position], listener);
    }

    override fun getItemCount(): Int {
        return notes.size;
    }

    
    class NoteViewHolder(val noteView: View) : RecyclerView.ViewHolder(noteView){

        fun bind(note: Note, listener : (Note) -> Unit) = with(noteView){
            noteItemCaption.text = note.caption
            setOnClickListener{ listener(note) }
        }
    }
}
