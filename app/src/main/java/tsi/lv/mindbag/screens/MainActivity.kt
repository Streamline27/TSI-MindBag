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
import tsi.lv.mindbag.di.HelloDagger
import tsi.lv.mindbag.domain.Note
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    var names = listOf(
            Note(caption = "Sanja"),
            Note(caption = "Petja")
    );

    @Inject
    lateinit var helloDager : HelloDagger;

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        App.injector.inject(this)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)

        notesListView.layoutManager = LinearLayoutManager(this)
        notesListView.adapter = NoteListAdapter(this, names, this::onNoteItemClick, this::onNoteItemLongClick)

        supportActionBar?.title = "Notes"
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
        startActivity(intent)
    }

    fun onNoteItemLongClick(note : Note) : Boolean{
        DeleteNoteDialog().show(fragmentManager, "Delete")
        return true
    }


    private fun onMenuSettingsClick() {
        Toast.makeText(this, "Settings click", Toast.LENGTH_SHORT).show()
    }

    fun onMenuAddNoteClick() {
        AddNoteDialog().show(fragmentManager, "Create")
    }
}
