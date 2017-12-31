package tsi.lv.mindbag.screens.books

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_books.*
import tsi.lv.mindbag.App

import tsi.lv.mindbag.R
import tsi.lv.mindbag.model.Model
import tsi.lv.mindbag.model.domain.Book
import tsi.lv.mindbag.mutableCopyOf
import javax.inject.Inject


class BooksFragment : Fragment(), AddBookDialog.OnAddBookListener, DeleteBookDialog.OnDeleteBookListener{


    var mAdapter : BooksListAdapter? = null

    @Inject
    lateinit var model : Model

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        App.injector.inject(this)

        val view = inflater!!.inflate(R.layout.fragment_books, container, false)
        val bookListView = view.findViewById<RecyclerView>(R.id.bookListView)
        val plusBookButton = view.findViewById<ImageButton>(R.id.plusBookButton)

        bookListView.layoutManager = LinearLayoutManager(activity)

        mAdapter = BooksListAdapter(mutableCopyOf(model.getBooks()), this::onBookItemClick, this::onBookItemLongClick)
        mAdapter?.setSelectedBook(model.getSelectedBook())
        bookListView.adapter = mAdapter

        plusBookButton.setOnClickListener(this::onPlusBookButtonClick)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if  (savedInstanceState != null) {
            mAdapter?.selectedPosition = savedInstanceState.getInt("SELECTED_POSITION")
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putInt("SELECTED_POSITION", mAdapter?.selectedPosition ?: 0)
    }

    /*
             * UI reactions
             */
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
        with(activity as OnBookDrawerListener) {
            onBookSelect(book)
        }
        activity.drawerLayout.closeDrawer(Gravity.LEFT, true);
    }

    override fun onCreateBookItemClick(title: String) {
        val book = Book(title)
        model.createBook(book)
        mAdapter?.add(book)
    }

    override fun onDeleteBookItemClick(id: Int) {

        if (model.getSelectedBook().id!!.equals(id)) return

        model.deleteBook(id)
        mAdapter?.delete(id)
    }

    interface OnBookDrawerListener{
        fun onBookSelect(book : Book)
    }
}
