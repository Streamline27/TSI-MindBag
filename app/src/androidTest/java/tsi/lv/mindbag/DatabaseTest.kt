package tsi.lv.mindbag

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertTrue
import tsi.lv.mindbag.db.AppDatabase
import org.junit.Test
import org.junit.runner.RunWith
import tsi.lv.mindbag.domain.Note

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    @Test
    fun testDatabase() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        val db = Room.databaseBuilder(appContext, AppDatabase::class.java, "db-name").build();

        db.noteDao().create(Note(caption = "Caption", content = "content"))

        var notes = db.noteDao().getAll();

        assertTrue(notes.isNotEmpty());
    }
}