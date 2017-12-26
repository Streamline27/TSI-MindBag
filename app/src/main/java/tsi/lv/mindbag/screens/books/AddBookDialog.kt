package tsi.lv.mindbag.screens.books

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.widget.EditText

/**
 * Created by Vladislav on 12/27/2017.
 */
/* Todo: Create abstract class for this */
class AddBookDialog : DialogFragment() {

    var onAddBookListener : OnAddBookListener? = null

    companion object {
        fun newInstance(targetFragment: Fragment) : AddBookDialog {
            val dialog = AddBookDialog()
            dialog.setTargetFragment(targetFragment, 1)
            return dialog
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        onAddBookListener = targetFragment as OnAddBookListener

        val editText = EditText(activity)

        val dialog = AlertDialog.Builder(activity)
                .setTitle("Create category")
                .setView(editText)
                .setPositiveButton("Create", { _, _ -> onAddBookListener?.onCreateBookItemClick(editText.text.toString()) })
                .setNegativeButton("Cancel", { _, _ -> })
                .create()

        return dialog
    }


    interface OnAddBookListener {
        fun onCreateBookItemClick(title : String)
    }
}