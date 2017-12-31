package tsi.lv.mindbag.model.domain

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Vladislav on 12/26/2017.
 */

@Entity
class Book() {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    constructor(title : String, id : Int? = null): this() {
        this.title = title
        this.id = id
    }
}