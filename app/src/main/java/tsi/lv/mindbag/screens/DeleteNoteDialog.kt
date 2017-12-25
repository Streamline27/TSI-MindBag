package tsi.lv.mindbag.screens

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface.BUTTON_POSITIVE
import android.graphics.Color.RED
import android.os.Bundle
import android.widget.Toast

/**
 * Created by Vladislav on 12/24/2017.
 */
class DeleteNoteDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog =AlertDialog.Builder(activity)
                .setTitle("Delete note")
                .setMessage("Are you sure you want to delete it?")
                .setPositiveButton("Delete", { _, _ -> Toast.makeText(activity, "Delete click", Toast.LENGTH_SHORT).show() })
                .setNegativeButton("Cancel", { _, _ -> Toast.makeText(activity, "Cancel click", Toast.LENGTH_SHORT).show() })
                .create()

        dialog.setOnShowListener( { dialog.getButton(BUTTON_POSITIVE).setTextColor(RED)})

        return dialog
    }
}