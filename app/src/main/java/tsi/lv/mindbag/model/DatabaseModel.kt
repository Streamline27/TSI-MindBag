package tsi.lv.mindbag.model

import android.app.Activity
import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import tsi.lv.mindbag.model.database.AppDatabase
import tsi.lv.mindbag.model.database.BookDao
import tsi.lv.mindbag.model.database.NoteDao
import tsi.lv.mindbag.model.domain.Book
import tsi.lv.mindbag.model.domain.Note

/**
 * Created by Vladislav on 12/31/2017.
 */
class DatabaseModel(val appContext: Context) : Model {

    val noteDao: NoteDao
    val bookDao: BookDao

    var selectedBookId = 0

    init {
        val db = Room.databaseBuilder(appContext, AppDatabase::class.java, "db-name").allowMainThreadQueries().build();

        bookDao = db.bookDao()
        noteDao = db.noteDao()


        val preferences = appContext.getSharedPreferences("MINDBAG_PREF", Context.MODE_PRIVATE)
        selectedBookId = preferences.getInt("SELECTED_BOOK", 0);

    }


    override fun selectBook(id: Int) {
        selectedBookId = id

        val preferences = appContext.getSharedPreferences("MINDBAG_PREF", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt("SELECTED_BOOK", selectedBookId)
        editor.apply()

    }

    override fun getSelectedBook(): Book {
        return bookDao.findById(selectedBookId)
    }

    override fun getBooks(): List<Book> {
        return bookDao.getAll()
    }

    override fun createBook(book: Book) {
        bookDao.create(book)
        book.id = bookDao.getLastAutoIncrement()
    }

    override fun deleteBook(id: Int) {
        bookDao.deleteById(id)
        noteDao.deleteNotes(id)
    }

    override fun getNotes(): List<Note> {
        return noteDao.getAllByBookId(selectedBookId)
    }

    override fun getNote(id: Int): Note? {
        return noteDao.findById(id)
    }

    override fun updateNote(note: Note) {
        noteDao.createOrUpdate(note)
    }

    override fun createNote(note: Note) {
        noteDao.createOrUpdate(note)
        note.id = noteDao.getLastAutoIncrement()
    }

    override fun deleteNote(id: Int) {
        noteDao.deleteNote(id)
    }
}