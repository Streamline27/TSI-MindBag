package tsi.lv.mindbag.utilities


import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.widget.EditText
import android.widget.Toast

/**
 * Created by Vladislav on 12/25/2017.
 */


fun createAddNoteDialog(ctx : Context, positivieClickAction: (DialogInterface, Int) -> Unit) : Dialog {

    val editText = EditText(ctx)

    val dialog = AlertDialog.Builder(ctx)
            .setTitle("Create note")
            .setView(editText)
            .setPositiveButton("Create", positivieClickAction)
            .setNegativeButton("Cancel", { _, _ -> Toast.makeText(ctx, "Cancel click", Toast.LENGTH_SHORT).show() })
            .create()

    return dialog
}

fun createDeleteNoteDialog(ctx: Context, deleteClickAction : (DialogInterface, Int) -> Unit) : Dialog {

    val dialog =AlertDialog.Builder(ctx)
            .setTitle("Delete note")
            .setMessage("Are you sure you want to delete it?")
            .setPositiveButton("Delete", deleteClickAction)
            .setNegativeButton("Cancel", { _, _ -> Toast.makeText(ctx, "Cancel click", Toast.LENGTH_SHORT).show() })
            .create()

    dialog.setOnShowListener( { dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.RED)})

    return dialog

}
