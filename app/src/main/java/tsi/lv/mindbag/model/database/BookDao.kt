package tsi.lv.mindbag.model.database

import android.arch.persistence.room.*
import tsi.lv.mindbag.model.domain.Book

/**
 * Created by Vladislav on 12/31/2017.
 */
@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAll() : List<Book>

    @Query("SELECT * FROM book WHERE id = :arg0")
    fun findById(id : Int) : Book

    @Query("DELETE FROM book WHERE id = :arg0")
    fun deleteById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(book : Book);

    @Query("SELECT last_insert_rowid()")
    fun getLastAutoIncrement() : Int
}