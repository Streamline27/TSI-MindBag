package tsi.lv.mindbag.screens.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.SearchView

import kotlinx.android.synthetic.main.activity_search.*
import tsi.lv.mindbag.*
import tsi.lv.mindbag.model.Model
import tsi.lv.mindbag.model.domain.Book
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    var mAdapter : SearchListAdapter? = null

    @Inject
    lateinit var model : Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        App.injector.inject(this)

        searchListView.layoutManager = LinearLayoutManager(this)
        mAdapter = SearchListAdapter(mutableCopyOf(model.getBooks()), this::onSearchItemClick)
        searchListView.adapter = mAdapter
        searchBackButton.setOnClickListener(this::onArrowButtonClick)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mAdapter?.filter?.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mAdapter?.filter?.filter(newText)
                return false
            }

        })
    }

    private fun onSearchItemClick(book : Book) {
        model.selectBook(book.id!!)
        setResult(RESULT_CODE_SEARCH_BOOK_SELECTED)
        finish()
    }

    private fun onArrowButtonClick(view: View) {
        setResult(RESULT_CODE_SEARCH_BACK_CLICK)
        finish()
    }

}
