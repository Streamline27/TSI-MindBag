package tsi.lv.mindbag.screens

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note.*
import tsi.lv.mindbag.R

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        setSupportActionBar(noteToolbar)

        supportActionBar?.title = "Note name"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        noteEditFab.setOnClickListener { onEditButtonClick() }
    }

    fun onEditButtonClick() {
        Toast.makeText(this, "FAB click", Toast.LENGTH_SHORT).show()
    }

}
