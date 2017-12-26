package tsi.lv.mindbag.screens.notes

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface.BUTTON_POSITIVE
import android.graphics.Color.RED
import android.os.Bundle

/**
 * Created by Vladislav on 12/24/2017.
 */
class DeleteNoteDialog : DialogFragment() {

    var onDeleteNoteListener : OnDeleteNoteListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onDeleteNoteListener = activity as OnDeleteNoteListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val id = arguments.getInt("id") as Int

        val dialog = AlertDialog.Builder(activity)
                .setTitle("Delete note")
                .setMessage("Are you sure you want to delete it?")
                .setPositiveButton("Delete", { _,_ -> onDeleteNoteListener?.onDeleteNoteClick(id) })
                .setNegativeButton("Cancel", { _,_ -> })
                .create()

        dialog.setOnShowListener( { dialog.getButton(BUTTON_POSITIVE).setTextColor(RED)})

        return dialog
    }

    fun addNoteIdArg(id : Int?) {
        val args = arguments ?: Bundle()
        args.putInt("id", id?: -1)
        arguments = args
    }

    interface OnDeleteNoteListener {
        fun onDeleteNoteClick(id : Int)
    }
}