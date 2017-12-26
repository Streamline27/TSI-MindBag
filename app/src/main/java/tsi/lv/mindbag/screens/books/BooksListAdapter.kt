package tsi.lv.mindbag.screens.books

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.book_item.view.*
import kotlinx.android.synthetic.main.note_item.view.*
import tsi.lv.mindbag.R
import tsi.lv.mindbag.model.domain.Book
import tsi.lv.mindbag.model.domain.Note

/**
 * Created by Vladislav on 12/26/2017.
 */
class BooksListAdapter(val books : MutableList<Book>,
                       val clickListener: (Book) -> Unit,
                       val longClickListener: (Book) -> Boolean) : RecyclerView.Adapter<BooksListAdapter.BookViewHolder>() {

    override fun onBindViewHolder(holder: BooksListAdapter.BookViewHolder, position: Int) {
        holder.bind(this.books[position], clickListener, longClickListener)
    }

    override fun getItemCount(): Int = books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksListAdapter.BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(view)
    }


    class BookViewHolder(val bookView: View) : RecyclerView.ViewHolder(bookView){

        fun bind(book : Book,
                 clickListener     : (Book) -> Unit,
                 longClickListener : (Book) -> Boolean) = with(bookView){

            bookItemTitle.text = book.title
            setOnClickListener{ clickListener(book) }
            setOnLongClickListener{ longClickListener(book) }
        }
    }
}