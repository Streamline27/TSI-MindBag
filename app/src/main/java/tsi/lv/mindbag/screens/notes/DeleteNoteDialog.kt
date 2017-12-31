package tsi.lv.mindbag.screens.notes

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface.BUTTON_POSITIVE
import android.graphics.Color.RED
import android.os.Bundle
import tsi.lv.mindbag.EXTRA_DIALOG_DELETE_NOTE_ID

/**
 * Created by Vladislav on 12/24/2017.
 */
/* Todo: Create abstract class for this */
class DeleteNoteDialog : DialogFragment() {

    var onDeleteNoteListener : OnDeleteNoteListener? = null

    companion object {
        fun newInstance(id : Int?) : DeleteNoteDialog {
            val dialog = DeleteNoteDialog()

            val args = dialog.arguments ?: Bundle()
            args.putInt(EXTRA_DIALOG_DELETE_NOTE_ID, id?: -1)
            dialog.arguments = args

            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onDeleteNoteListener = activity as OnDeleteNoteListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val id = arguments.getInt(EXTRA_DIALOG_DELETE_NOTE_ID) as Int

        val dialog = AlertDialog.Builder(activity)
                .setTitle("Delete note")
                .setMessage("Are you sure you want to delete it?")
                .setPositiveButton("Delete", { _,_ -> onDeleteNoteListener?.onDeleteNoteClick(id) })
                .setNegativeButton("Cancel", { _,_ -> })
                .create()

        dialog.setOnShowListener( { dialog.getButton(BUTTON_POSITIVE).setTextColor(RED)})

        return dialog
    }

    interface OnDeleteNoteListener {
        fun onDeleteNoteClick(id : Int)
    }
}