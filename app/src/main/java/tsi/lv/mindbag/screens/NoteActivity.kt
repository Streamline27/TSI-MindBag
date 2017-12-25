package tsi.lv.mindbag.screens

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note.*
import tsi.lv.mindbag.R

class NoteActivity : AppCompatActivity() {

    var mEditModeOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        setSupportActionBar(noteToolbar)

        supportActionBar?.title = "Note name"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        noteToolbar.setNavigationOnClickListener { onUpOrCheckButtonClick() }
        noteEditFab.setOnClickListener { onEditButtonClick() }
    }

    fun onEditButtonClick() {
        toggleEditMode()
    }

    fun onUpOrCheckButtonClick() {
        if (mEditModeOn) {
            toggleReadMode()
        }
        else navigateUp()
    }

    fun toggleEditMode() {
        mEditModeOn = true
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_check)
        noteEditFab.hide()
    }

    fun toggleReadMode() {
        mEditModeOn = false
        supportActionBar?.setHomeAsUpIndicator(null)
        noteEditFab.show()
    }

    fun navigateUp() {
        NavUtils.navigateUpTo(this, NavUtils.getParentActivityIntent(this))
    }

}
