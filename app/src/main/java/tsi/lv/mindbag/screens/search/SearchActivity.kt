package tsi.lv.mindbag.screens.search

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.SearchView
import tsi.lv.mindbag.R

import kotlinx.android.synthetic.main.activity_search.*
import tsi.lv.mindbag.App
import tsi.lv.mindbag.model.Model
import tsi.lv.mindbag.model.domain.Book
import tsi.lv.mindbag.mutableCopyOf
import tsi.lv.mindbag.screens.notes.MainActivity
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
        
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent)
    }

}
