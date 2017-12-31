package tsi.lv.mindbag.screens.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import kotlinx.android.synthetic.main.item_book.view.*
import kotlinx.android.synthetic.main.item_search.view.*
import tsi.lv.mindbag.R
import tsi.lv.mindbag.model.domain.Book
import tsi.lv.mindbag.mutableCopyOf

/**
 * Created by Vladislav on 12/31/2017.
 */
class SearchListAdapter(val books : MutableList<Book>,
                        val clickListener: (Book) -> Unit) : RecyclerView.Adapter<SearchListAdapter.SearchViewHolder>(), Filterable{


    var selectedPosition : Int = 0
    var booksFiltered : MutableList<Book> = books

    override fun onBindViewHolder(holder: SearchListAdapter.SearchViewHolder, position: Int) {
        holder.itemView.setSelected(selectedPosition == position);
        holder.bind(this.booksFiltered[position], clickListener)
    }

    override fun getItemCount(): Int = booksFiltered.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListAdapter.SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
    }


    class SearchViewHolder(val searchView: View) : RecyclerView.ViewHolder(searchView){

        fun bind(book : Book,
                 clickListener : (Book) -> Unit) = with(searchView){
            searchItemCaption.text = book.title
            setOnClickListener{ clickListener(book) }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                var filtered : MutableList<Book>
                if (charSequence.isEmpty()) {
                    filtered = books
                }
                else {
                    filtered = mutableCopyOf(books.filter { it.title!!.toLowerCase().contains(charString.toLowerCase()) } )
                }
                val result = FilterResults()
                result.values = filtered
                return result
            }

            override fun publishResults(p0: CharSequence?, result: FilterResults) {
                booksFiltered = result.values as MutableList<Book>
                notifyDataSetChanged()
            }

        }
    }
}