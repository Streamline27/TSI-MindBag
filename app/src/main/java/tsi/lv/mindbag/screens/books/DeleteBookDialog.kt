package tsi.lv.mindbag.screens.books

import android.app.AlertDialog
import android.app.Dialog

import android.content.DialogInterface.BUTTON_POSITIVE
import android.graphics.Color.RED
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import tsi.lv.mindbag.EXTRA_DIALOG_DELETE_BOOK_ID
import tsi.lv.mindbag.EXTRA_DIALOG_DELETE_BOOK_TITLE
import tsi.lv.mindbag.model.domain.Book

/**
 * Created by Vladislav on 12/27/2017.
 */
class DeleteBookDialog : DialogFragment() {

    var onDeleteBookListener: OnDeleteBookListener? = null

    companion object {
        fun newInstance(book: Book, targetFragment: Fragment) : DeleteBookDialog {
            val dialog = DeleteBookDialog()

            val args = dialog.arguments ?: Bundle()
            args.putInt(EXTRA_DIALOG_DELETE_BOOK_ID, book.id?: -1)
            args.putString(EXTRA_DIALOG_DELETE_BOOK_TITLE, book.title)
            dialog.arguments = args

            dialog.setTargetFragment(targetFragment, 1)

            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onDeleteBookListener = targetFragment as OnDeleteBookListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val id = arguments.getInt(EXTRA_DIALOG_DELETE_BOOK_ID) as Int
        val titile = arguments.getString(EXTRA_DIALOG_DELETE_BOOK_TITLE)

        val dialog = AlertDialog.Builder(activity)
                .setTitle("Delete category")
                .setMessage("Are you sure you want to delete $titile?")
                .setPositiveButton("Delete", { _,_ -> onDeleteBookListener?.onDeleteBookItemClick(id) })
                .setNegativeButton("Cancel", { _,_ -> })
                .create()

        dialog.setOnShowListener( { dialog.getButton(BUTTON_POSITIVE).setTextColor(RED)})

        return dialog
    }

    interface OnDeleteBookListener {
        fun onDeleteBookItemClick(id : Int)
    }

}