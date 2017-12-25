package tsi.lv.mindbag

/**
 * Created by Vladislav on 12/24/2017.
 */

inline fun perform(f: () -> Unit) : Boolean {
    f()
    return true
}