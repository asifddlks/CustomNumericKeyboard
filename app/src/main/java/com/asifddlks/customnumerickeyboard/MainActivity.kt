package com.asifddlks.customnumerickeyboard

import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
        val textView = findViewById<TextView>(R.id.textView)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val keyboard = findViewById<CustomNumericKeyboard>(R.id.keyboard)

        editText.setCustomNumericKeyboard(textColor = R.color.average_color, keyboard = keyboard, nestedScrollView = nestedScrollView)
    }
}