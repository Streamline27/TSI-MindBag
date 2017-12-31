package tsi.lv.mindbag.screens.notes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_note.view.*
import tsi.lv.mindbag.R
import tsi.lv.mindbag.model.domain.Note


class NoteListAdapter(val notes  : MutableList<Note>,
                      val clickListener     : (Note) -> Unit,
                      val longClickListener : (Note) -> Boolean) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false);
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

    fun set(notes : List<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)
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
