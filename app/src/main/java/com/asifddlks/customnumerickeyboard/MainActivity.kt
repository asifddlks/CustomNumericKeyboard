package com.asifddlks.customnumerickeyboard

import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val keyboard = findViewById<com.asifddlks.customnumerickeyboard.CustomNumericKeyboard>(R.id.keyboard)

        /*editText.setRawInputType(InputType.TYPE_CLASS_NUMBER)
        editText.setTextIsSelectable(true)
        editText.showSoftInputOnFocus = false

        // pass the InputConnection from the EditText to the keyboard

        // pass the InputConnection from the EditText to the keyboard
        val ic = editText.onCreateInputConnection(EditorInfo())
        keyboard.setInputConnectionToEditText(ic)*/

        editText.setCustomNumericKeyboard(keyboard)

    }
}