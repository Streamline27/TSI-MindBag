package tsi.lv.mindbag.domain

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Note() {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "caption")
    var caption: String? = null

    @ColumnInfo(name = "content")
    var content: String? = null

    constructor(caption: String, content: String): this() {
        this.caption = caption
        this.content = content
    }

    constructor(caption: String) : this(){
        this.caption = caption;
    }
}