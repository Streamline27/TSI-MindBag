package tsi.lv.mindbag

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import tsi.lv.mindbag.di.HelloDagger
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    var names = listOf("Vasja", "Pethja");

    @Inject
    lateinit var helloDager : HelloDagger;

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        App.injector.inject(this);
        setContentView(R.layout.activity_main)

        notesListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names + helloDager.getHello());
    }
}
