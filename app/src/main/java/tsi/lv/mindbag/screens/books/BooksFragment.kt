package tsi.lv.mindbag.screens.books

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_books.*

import tsi.lv.mindbag.R
import tsi.lv.mindbag.model.domain.Book


class BooksFragment : Fragment(), AddBookDialog.OnAddBookListener, DeleteBookDialog.OnDeleteBookListener{

    val books = mutableListOf(
            Book("English", 1),
            Book("Latvian", 2),
            Book("Estonian", 3),
            Book("Study", 4),
            Book("University", 5),
            Book("TTI", 6),
            Book("Sick leave", 7)
    )

    var mAdapter : BooksListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_books, container, false)
        val bookListView = view.findViewById<RecyclerView>(R.id.bookListView)
        val plusBookButton = view.findViewById<ImageButton>(R.id.plusBookButton)

        bookListView.layoutManager = LinearLayoutManager(activity)

        mAdapter = BooksListAdapter(books, this::onBookItemClick, this::onBookItemLongClick)
        bookListView.adapter = mAdapter

        plusBookButton.setOnClickListener(this::onPlusBookButtonClick)

        return view
    }


    fun onPlusBookButtonClick(view: View) {
        val dialog = AddBookDialog.newInstance(this)
        dialog.show(fragmentManager, "Create")
    }



    fun onBookItemLongClick(book: Book) : Boolean {
        val dialog = DeleteBookDialog.newInstance(book, this)
        dialog.show(fragmentManager, "Create")

        return true
    }

    fun onBookItemClick(book: Book) {
        Toast.makeText(context,"Click ${book.title}", Toast.LENGTH_LONG).show()
    }

    override fun onCreateBookItemClick(title: String) {
        Toast.makeText(context,"Create click", Toast.LENGTH_LONG).show()
    }

    override fun onDeleteBookItemClick(id: Int) {
        Toast.makeText(context,"Delete click", Toast.LENGTH_LONG).show()
    }
}
