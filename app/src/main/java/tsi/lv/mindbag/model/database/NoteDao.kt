package tsi.lv.mindbag.model.database


import android.arch.persistence.room.*
import tsi.lv.mindbag.model.domain.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note WHERE bookId = :arg0")
    fun getAllByBookId(bookId : Int) : List<Note>;

    @Query("SELECT * FROM note WHERE id = :arg0")
    fun findById(id : Int) : Note;

    @Query("SELECT last_insert_rowid()")
    fun getLastAutoIncrement() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createOrUpdate(note : Note);

    @Query("DELETE FROM note WHERE id = :arg0")
    fun deleteNote(id : Int)
}