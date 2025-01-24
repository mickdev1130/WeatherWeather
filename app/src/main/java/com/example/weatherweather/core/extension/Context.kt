package com.example.weatherweather.core.extension

import android.app.AlertDialog
import android.content.Context

fun Context.showDialogDynamic(
    message: String,
    neutralButtonText: String = "OK",
    dismissCallback: () -> Unit = {}
) {
    AlertDialog.Builder(this)
        .setMessage(message)
        .setNeutralButton(neutralButtonText) { dialog, _ ->
            dialog.dismiss()
            dismissCallback()
        }
        .setCancelable(false)
        .show()
}