package com.example.traningcomposeapp.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle

object Router {

    fun launchActivity(
        context: Context,
        activity: Class<*>,
        flags: Int? = null,
        extras: Bundle? = null
    ) {
        val intent = Intent(context, activity)
        flags?.let { intent.flags = it }
        extras?.let { intent.putExtras(it) }
        context.startActivity(intent)
    }
}