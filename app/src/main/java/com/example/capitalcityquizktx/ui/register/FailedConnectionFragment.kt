package com.example.capitalcityquizktx.ui.register

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import com.example.capitalcityquizktx.R

class FailedConnectionFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("""
                Possible causes: 
                    - You are not connected to the internet. 
                    - The server is not responding. 
            """.trimIndent())
                .setPositiveButton(getString(R.string.ok)) { dialog, id -> dialog.dismiss() }
                .setTitle("Unable to connect")
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}