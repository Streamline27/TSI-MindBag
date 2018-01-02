package tsi.lv.mindbag.model

import android.content.SharedPreferences
import com.nhaarman.mockito_kotlin.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import tsi.lv.mindbag.model.database.BookDao
import tsi.lv.mindbag.model.database.NoteDao
import tsi.lv.mindbag.model.domain.Book
import tsi.lv.mindbag.model.domain.Note

/**
 * Created by Vladislav on 1/3/2018.
 */
class DatabaseModelTest {

    val preferences = mock<SharedPreferences>()
    val editor = mock<SharedPreferences.Editor>()
    val bookDao = mock<BookDao>()
    val noteDao = mock<NoteDao>()

    @Before
    fun setUp() {
        given( preferences.edit()) .willReturn( editor )
    }

    @Test
    fun getSelectedBookId() {

        given { preferences.getInt("SELECTED_BOOK", 0)  } .willReturn (1)
        given { bookDao.findById(1)} .willReturn( Book() )

        modelWithMocks().getSelectedBook()

        verify( bookDao ).findById(1)
    }

    @Test
    fun selectBook() {

        modelWithMocks().selectBook(1)

        verify( preferences, atLeastOnce()).edit()
        verify( editor, atLeastOnce()) .putInt("SELECTED_BOOK", 1)
        verify( editor, atLeastOnce()) .apply()

    }

    @Test
    fun getSelectedBook() {

        val model = modelWithMocks()

        model.selectBook(1)
        model.getSelectedBook()

        verify(bookDao) .findById(1)
    }

    @Test
    fun getBooks() {

        val books = listOf(Book("one", 1), Book("two", 2))
        given { bookDao.getAll() } .willReturn( books )

        val resultBooks = modelWithMocks().getBooks()

        resultBooks.assertContains(books[0])
        resultBooks.assertContains(books[1])
    }

    @Test
    fun createBook() {

        val book = Book("", 1)
        modelWithMocks().createBook(book)

        verify(bookDao).create(book)
    }


    @Test
    fun deleteBook() {

        modelWithMocks().deleteBook(1)
        verify(bookDao).deleteById(1)
    }

    @Test
    fun getNotes() {

        val notes = listOf(
                Note("", "", 1, 2),
                Note("", "", 2, 2))

        given { noteDao.getAllByBookId(2) } .willReturn(notes)

        val model = modelWithMocks()

        model.selectBook(2)
        val notesResult = model.getNotes()

        notesResult.assertContains(notes[0])
        notesResult.assertContains(notes[1])
    }

    @Test
    fun getNote() {

        modelWithMocks().getNote(1)
        verify(noteDao, atLeastOnce()).findById(1)
    }

    @Test
    fun updateNote() {

        val note = Note("", "", 1)
        modelWithMocks().updateNote(note)

        verify(noteDao, atLeastOnce()).createOrUpdate(note)
    }

    @Test
    fun createNote() {

        val note = Note("", "", 1)
        modelWithMocks().createNote(note)

        verify(noteDao, atLeastOnce()).createOrUpdate(note)
    }

    @Test
    fun deleteNote() {

        modelWithMocks().deleteNote(1)
        verify(noteDao, atLeastOnce()).deleteNote(1)
    }

    private fun modelWithMocks() = DatabaseModel(preferences, noteDao, bookDao)

    private fun List<Book>.assertContains(book : Book) = assertNotNull(this.find { book.id!!.equals(it.id) })
    private fun List<Note>.assertContains(note : Note) = assertNotNull(this.find { note.id!!.equals(it.id) })
}
