package tsi.lv.mindbag.model.database


import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import tsi.lv.mindbag.model.domain.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll() : List<Note>;

    @Query("SELECT * FROM note WHERE id = :arg0")
    fun findById(id : Int) : Note;

    @Query("SELECT last_insert_rowid()")
    fun getLastAutoIncrement() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(note : Note);
}