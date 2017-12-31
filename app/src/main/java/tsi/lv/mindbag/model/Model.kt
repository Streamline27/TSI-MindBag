package tsi.lv.mindbag.model

import tsi.lv.mindbag.model.domain.Book
import tsi.lv.mindbag.model.domain.Note

/**
 * Created by Vladislav on 12/25/2017.
 */
interface Model {

    fun selectBook(id: Int)
    fun getSelectedBook() : Book
    fun getBooks() : List<Book>
    fun createBook(book : Book)
    fun deleteBook(id : Int)

    fun getNotes() : List<Note>
    fun getNote(id : Int) : Note?

    fun updateNote(note : Note)
    fun createNote(note : Note)

    fun deleteNote(id : Int)
}