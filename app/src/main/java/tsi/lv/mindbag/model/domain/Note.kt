package tsi.lv.mindbag.model.domain

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Note() {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

    @ColumnInfo(name = "caption")
    var caption: String? = null

    @ColumnInfo(name = "content")
    var content: String? = null

    var bookId : Int? = null

    constructor(caption: String, content: String? = null, id : Int? = null, bookId: Int? = null): this() {
        this.caption = caption
        this.content = content
        this.id = id
        this.bookId = bookId
    }
}