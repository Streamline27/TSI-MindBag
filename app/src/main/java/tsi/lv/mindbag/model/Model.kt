package tsi.lv.mindbag.model

import tsi.lv.mindbag.model.domain.Note

/**
 * Created by Vladislav on 12/25/2017.
 */
interface Model {

    fun getNotes() : List<Note>
    fun getNote(id : Int) : Note?

    fun updateNote(note : Note)
    fun createNote(note : Note)

    fun deleteNote(id : Int)
}