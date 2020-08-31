package com.example.core.utils

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import com.example.core.BaseApplication.Companion.currentApplication


private val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics

fun Float.dp2px() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)

object Utils {
    @JvmOverloads
    fun toast(string: String?, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(currentApplication, string, duration).show()
    }
}
