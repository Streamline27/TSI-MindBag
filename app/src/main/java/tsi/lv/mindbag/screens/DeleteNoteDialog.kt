package tsi.lv.mindbag.screens

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface.BUTTON_POSITIVE
import android.graphics.Color.RED
import android.os.Bundle
import android.widget.Toast
import tsi.lv.mindbag.domain.Note

/**
 * Created by Vladislav on 12/24/2017.
 */
class DeleteNoteDialog : DialogFragment() {

    var onDeleteNoteListener : OnDeleteNoteListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onDeleteNoteListener = activity as DeleteNoteDialog.OnDeleteNoteListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val caption = arguments.getString("caption") as String

        val dialog =AlertDialog.Builder(activity)
                .setTitle("Delete note")
                .setMessage("Are you sure you want to delete it?")
                .setPositiveButton("Delete", { _,_ -> onDeleteNoteListener?.onDeleteNoteClick(caption) })
                .setNegativeButton("Cancel", { _,_ -> })
                .create()

        dialog.setOnShowListener( { dialog.getButton(BUTTON_POSITIVE).setTextColor(RED)})

        return dialog
    }

    interface OnDeleteNoteListener {
        fun onDeleteNoteClick(caption : String)
    }
}