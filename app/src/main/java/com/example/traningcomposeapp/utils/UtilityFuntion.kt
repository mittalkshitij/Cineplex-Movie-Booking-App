package com.example.traningcomposeapp.utils

import android.content.Context

fun pxToDp(px: Float, context: Context): Float {
    return px / (context.resources.displayMetrics.densityDpi / 160f)
}