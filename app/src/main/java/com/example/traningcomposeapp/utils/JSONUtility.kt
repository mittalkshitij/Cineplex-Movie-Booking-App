package com.example.traningcomposeapp.utils

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader

fun readJSONFromAssets(context: Context, path: String): String {
    val identifier = "[ReadJSON]"
    return try {
        val file = context.assets.open(path)
        Log.i(
            identifier,
            "Found File: $file.",
        )
        val bufferedReader = BufferedReader(InputStreamReader(file))
        val stringBuilder = StringBuilder()
        bufferedReader.useLines { lines ->
            lines.forEach {
                stringBuilder.append(it)
            }
        }
        Log.i(
            identifier,
            "stringBuilder: $stringBuilder",
        )
        stringBuilder.toString()
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e(
            identifier,
            "Error reading JSON: $e",
        )
        ""
    }
}