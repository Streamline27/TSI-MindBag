package tsi.lv.mindbag.model

import tsi.lv.mindbag.model.domain.Book
import tsi.lv.mindbag.model.domain.Note

/**
 * Created by Vladislav on 12/25/2017.
 */
class MockModel : Model {

    private val books = mutableListOf(
            Book("English", 1),
            Book("Latvian", 2),
            Book("Estonian", 3),
            Book("Study", 4),
            Book("University", 5),
            Book("TTI", 6),
            Book("Sick leave", 7)
    )

    private val names = mutableListOf(
            Note(caption = "Vojna", content = "Content of vojna", id = 1, bookId = 1),
            Note(caption = "Mir", content = "Content of mir", id = 2, bookId = 2),
            Note(caption = "Bulgakov", content = "Master", id = 3, bookId = 2)
    );

    private var selectedBook : Book = books[0]

    override fun getNotes(): List<Note> {
        return names.filter { it.bookId!!.equals(selectedBook.id) }
    }

    override fun getNote(id: Int) : Note?{
        return names.find { it.id!!.equals(id) }
    }

    override fun updateNote(note: Note) {
        val index = names.indexOfFirst { it.id!!.equals(note.id) }
        names[index] = note
    }

    override fun createNote(note: Note) {
        note.id = names
                .sortedWith(compareBy( { it.id  }))
                .last().id?.plus(1)

        names.add(note)
    }

    override fun deleteNote(id: Int) {
        names.removeAll { id.equals(it.id) }
    }


    override fun selectBook(id: Int) {
        val book = books.find { it.id == id }
        if (book != null ) selectedBook = book
    }

    override fun getSelectedBook() : Book {
        return selectedBook
    }

    override fun getBooks(): List<Book> {
       return books
    }

    override fun createBook(book: Book) {
        book.id = books
                .sortedWith(compareBy( { it.id  }))
                .last().id?.plus(1)
        books.add(book)
    }

    override fun deleteBook(id: Int) {
        books.removeAll { id.equals(it.id) }
        names.removeAll { id.equals(it.bookId) }
    }
}