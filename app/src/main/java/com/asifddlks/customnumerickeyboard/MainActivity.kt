package com.asifddlks.customnumerickeyboard

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.asifddlks.customnumerickeyboard.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /*val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
        val textView = findViewById<TextView>(R.id.textView)
        val editText1 = findViewById<EditText>(R.id.editText1)
        val editText2 = findViewById<EditText>(R.id.editText2)
        val button = findViewById<Button>(R.id.button)
        val keyboard = findViewById<CustomNumericKeyboard>(R.id.keyboard)

        *//*editText1.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                toast("focused")
            } else {
                toast("focuse lose")
            }
        }
        editText2.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                toast("focused")
            } else {
                toast("focuse lose")
            }
        }*/
        binding.editText1.setCustomNumericKeyboard(textColor = R.color.red, keyboard = binding.keyboard, hasDotKey = true, nestedScrollView = binding.nestedScrollView)
        binding.editText2.setCustomNumericKeyboard(textColor = R.color.average_color, keyboard = binding.keyboard, hasDotKey = false, nestedScrollView = binding.nestedScrollView)
    }
}