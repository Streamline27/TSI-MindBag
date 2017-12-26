package tsi.lv.mindbag.screens.notes

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.note_item.view.*
import tsi.lv.mindbag.R
import tsi.lv.mindbag.model.domain.Note


class NoteListAdapter(val ctx    : Context,
                      val notes  : MutableList<Note>,
                      val clickListener     : (Note) -> Unit,
                      val longClickListener : (Note) -> Boolean) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false);
        return NoteViewHolder(view);
    }

    override fun onBindViewHolder(holderNote: NoteViewHolder, position: Int) {
        holderNote.bind(this.notes[position], clickListener, longClickListener);
    }

    override fun getItemCount(): Int {
        return notes.size;
    }

    fun add(note : Note) {
        notes.add(note)
        notifyDataSetChanged()
    }

    fun delete(id: Int) {
        notes.removeAll { id.equals(it.id) }
        notifyDataSetChanged()
    }

    
    class NoteViewHolder(val noteView: View) : RecyclerView.ViewHolder(noteView){

        fun bind(note :  Note,
                 clickListener     : (Note) -> Unit,
                 longClickListener : (Note) -> Boolean) = with(noteView){

            noteItemCaption.text = note.caption
            setOnClickListener{ clickListener(note) }
            setOnLongClickListener{ longClickListener(note) }
        }
    }
}
