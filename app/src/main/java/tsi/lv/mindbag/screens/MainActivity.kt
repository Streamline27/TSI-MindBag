package tsi.lv.mindbag.screens

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import tsi.lv.mindbag.App
import tsi.lv.mindbag.R
import tsi.lv.mindbag.perform
import tsi.lv.mindbag.model.Model
import tsi.lv.mindbag.model.domain.Note
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AddNoteDialog.OnAddNoteListener, DeleteNoteDialog.OnDeleteNoteListener {

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

        notesListView.layoutManager = LinearLayoutManager(this)

        mAdapter = NoteListAdapter(this, mutableCopyOf(model.getNotes()), this::onNoteItemClick, this::onNoteItemLongClick);
        notesListView.adapter = mAdapter

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
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("id", note.id)
        startActivity(intent)
    }

    fun onNoteItemLongClick(note : Note) : Boolean {
        val deleteFragment = DeleteNoteDialog()
        deleteFragment.addNoteIdArg(note.id)

        deleteFragment.show(fragmentManager, "Delete")
        return true
    }


    private fun onMenuSettingsClick() {
        Toast.makeText(this, "Settings click", Toast.LENGTH_SHORT).show()
    }

    fun onMenuAddNoteClick() {
        AddNoteDialog().show(fragmentManager, "Create")
    }

    override fun onAddNoteClick(caption: String) {
        val note = Note(caption, content = "")
        model.createNote(note)
        mAdapter?.add(note)
    }

    override fun onDeleteNoteClick(id: Int) {
        model.deleteNote(id)
        mAdapter?.delete(id)
    }

    /*
        Private helper functions
     */

    fun mutableCopyOf(notes : List<Note> ) : MutableList<Note>{
        var copy = ArrayList<Note>()
        copy.addAll(notes)
        return copy
    }
}
