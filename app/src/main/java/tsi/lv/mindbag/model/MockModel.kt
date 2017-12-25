package tsi.lv.mindbag.model

import tsi.lv.mindbag.model.domain.Note

/**
 * Created by Vladislav on 12/25/2017.
 */
class MockModel : Model {

    var names = mutableListOf(
            Note(caption = "Vojna", content = "Content of vojna", id = 1),
            Note(caption = "Mir", content = "Content of mir", id = 2),
            Note(caption = "Bulgakov", content = "Master", id = 3)
    );

    override fun getNotes(): List<Note> {
        return names
    }

    override fun getNote(id: Int) : Note?{
        return names.find { it.id!!.equals(id) }
    }

    override fun updateNote(note: Note) {
        val index = names.indexOfFirst { it.id!!.equals(note.id) }
        names[index] = note
    }

    override fun createNote(note: Note) {
        note.id = names
                .sortedWith(compareBy( { it.id  }))
                .last().id?.plus(1)

        names.add(note)
    }

    override fun deleteNote(id: Int) {
        names.removeAll { id.equals(it.id) }
    }
}