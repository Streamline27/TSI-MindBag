package tsi.lv.mindbag.screens.books

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_book.view.*
import tsi.lv.mindbag.R
import tsi.lv.mindbag.model.domain.Book

/**
 * Created by Vladislav on 12/26/2017.
 */
class BooksListAdapter(val books : MutableList<Book>,
                       val clickListener: (Book) -> Unit,
                       val longClickListener: (Book) -> Boolean) : RecyclerView.Adapter<BooksListAdapter.BookViewHolder>() {

    var selectedPosition : Int = 0

    override fun onBindViewHolder(holder: BooksListAdapter.BookViewHolder, position: Int) {
        holder.itemView.setSelected(selectedPosition == position);
        holder.bind(this.books[position], clickListener, longClickListener)
    }

    override fun getItemCount(): Int = books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksListAdapter.BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view, this)
    }

    fun add(book : Book) {
        books.add(book)
        notifyDataSetChanged()
    }

    fun delete(id : Int) {
        val indexToRemove = books.indexOfFirst { id.equals(it.id)}
        if (selectedPosition > indexToRemove) selectedPosition--
        books.removeAt(indexToRemove)

        notifyDataSetChanged()
    }

    class BookViewHolder(val bookView: View, val adapter: BooksListAdapter) : RecyclerView.ViewHolder(bookView){

        fun bind(book : Book,
                 clickListener     : (Book) -> Unit,
                 longClickListener : (Book) -> Boolean) = with(bookView){

            bookItemTitle.text = book.title
            setOnLongClickListener{ longClickListener(book) }
            setOnClickListener{
                adapter.notifyItemChanged(adapter.selectedPosition)
                adapter.selectedPosition = adapterPosition
                adapter.notifyItemChanged(adapter.selectedPosition)

                clickListener(book)
            }
        }
    }

    fun setSelectedBook(selectedBook: Book) {
        val index = books.indexOfFirst { it.id!!.equals(selectedBook.id) }
        when {
            index == -1 -> selectedPosition = 0
            else        -> selectedPosition = index
        }
    }
}