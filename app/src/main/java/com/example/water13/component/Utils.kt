package com.example.water13.component

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.toast(content: String?) {
    content?.let {
        if(it.isNotEmpty()) {
            Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
        }
    }
}

fun Fragment.toast(content: String?) {
    content?.let {
        if (it.isNotEmpty()) {
            Toast.makeText(activity, content, Toast.LENGTH_SHORT).show()
        }
    }
}

fun Activity.toastUiThread(content: String?) {
    runOnUiThread { toast(content) }
}

fun Fragment.toastUiThread(content:String?) {
    activity?.toastUiThread(content)
}
