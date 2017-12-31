package tsi.lv.mindbag.screens.notes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.Gravity.START
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import tsi.lv.mindbag.App
import tsi.lv.mindbag.R
import tsi.lv.mindbag.perform
import tsi.lv.mindbag.model.Model
import tsi.lv.mindbag.model.domain.Book
import tsi.lv.mindbag.model.domain.Note
import tsi.lv.mindbag.mutableCopyOf
import tsi.lv.mindbag.screens.books.BooksFragment
import tsi.lv.mindbag.screens.content.ContentActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AddNoteDialog.OnAddNoteListener, DeleteNoteDialog.OnDeleteNoteListener, BooksFragment.OnBookDrawerListener{

    var mAdapter : NoteListAdapter? = null

    @Inject
    lateinit var model : Model

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        App.injector.inject(this)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)
        supportActionBar?.title = "Notes"

        // Init drawer
        val toggle =
                ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        mainToolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        notesListView.layoutManager = LinearLayoutManager(this)

        mAdapter = NoteListAdapter(mutableCopyOf(model.getNotes()), this::onNoteItemClick, this::onNoteItemLongClick);
        notesListView.adapter = mAdapter
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState?.putInt("SELECTED_POSITION", 0)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.action_add_note -> perform { onMenuAddNoteClick() }
        R.id.action_settings -> perform { onMenuSettingsClick() }
        else -> super.onOptionsItemSelected(item)
    }

    /*
        Reactions to UI events
     */
    fun onNoteItemClick(note : Note) {
        val intent = Intent(this, ContentActivity::class.java)
        intent.putExtra("id", note.id)
        startActivity(intent)
    }

    fun onNoteItemLongClick(note : Note) : Boolean {
        val deleteFragment = DeleteNoteDialog.newInstance(note.id)
        deleteFragment.show(fragmentManager, "Delete")

        return true
    }

    override fun onBackPressed() {
        when {
            drawerLayout.isDrawerVisible(GravityCompat.START) -> drawerLayout.closeDrawer(GravityCompat.START)
            else -> super.onBackPressed()
        }
    }

    private fun onMenuSettingsClick() {
        Toast.makeText(this, "Settings click", Toast.LENGTH_SHORT).show()
    }

    fun onMenuAddNoteClick() {
        AddNoteDialog().show(fragmentManager, "Create")
    }

    override fun onAddNoteClick(caption: String) {
        val note = Note(caption, content = "", bookId = model.getSelectedBook().id)
        model.createNote(note)
        mAdapter?.add(note)
    }

    override fun onDeleteNoteClick(id: Int) {
        model.deleteNote(id)
        mAdapter?.delete(id)
    }


    override fun onBookSelect(book: Book) {
        model.selectBook(book.id!!)
        mAdapter?.set(model.getNotes())
    }

}
