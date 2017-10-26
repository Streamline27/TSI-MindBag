package tsi.lv.mindbag.db


import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import tsi.lv.mindbag.domain.Note

@Database(entities = arrayOf(Note::class) , version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao;

}