package tsi.lv.mindbag.screens

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import tsi.lv.mindbag.App
import tsi.lv.mindbag.R
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

        App.injector.inject(this);
        setContentView(R.layout.activity_main)

        notesListView.layoutManager = LinearLayoutManager(this);
        notesListView.adapter = NoteAdapter(this, names + Note(helloDager.getHello()), {})

    }
}
