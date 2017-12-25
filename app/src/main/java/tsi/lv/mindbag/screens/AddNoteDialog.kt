package tsi.lv.mindbag.screens

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast

/**
 * Created by Vladislav on 12/24/2017.
 */
class AddNoteDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val editText = EditText(activity)

        val dialog = AlertDialog.Builder(activity)
                .setTitle("Create note")
                .setView(editText)
                .setPositiveButton("Create", { _, _ -> Toast.makeText(activity, "Create click", Toast.LENGTH_SHORT).show() })
                .setNegativeButton("Cancel", { _, _ -> Toast.makeText(activity, "Cancel click", Toast.LENGTH_SHORT).show() })
                .create()

        return dialog
    }
}