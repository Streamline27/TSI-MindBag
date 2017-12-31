package tsi.lv.mindbag

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import tsi.lv.mindbag.model.domain.Note

/**
 * Created by Vladislav on 12/24/2017.
 */

inline fun perform(f: () -> Unit) : Boolean {
    f()
    return true
}

fun hideKeyboard(activity: Activity,  view : View) {
    val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getRootView().getWindowToken(), 0);
}

fun showKeyboard(activity: Activity,  view : View) {
    val inputService = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputService.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
}

fun <T> mutableCopyOf(list : List<T> ) : MutableList<T>{
    var copy = ArrayList<T>()
    copy.addAll(list)
    return copy
}