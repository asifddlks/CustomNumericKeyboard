package com.asifddlks.customnumerickeyboard

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout


//
// Created by Asif Ahmed on 12/1/23.
//
class CustomNumericKeyboard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {
    // keyboard keys (buttons)
    /*private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    private var button5: Button? = null
    private var button6: Button? = null
    private var button7: Button? = null
    private var button8: Button? = null
    private var button9: Button? = null
    private var button0: Button? = null
    private var buttonDot: Button? = null
    private var buttonDelete: Button? = null*/

    // This will map the button resource id to the String value that we want to
    // input when that button is clicked.
    private var keyValues = SparseArray<String>()

    // Our communication link to the EditText
    private var inputConnection: InputConnection? = null

    // constructors
    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {

        // initialize buttons
        LayoutInflater.from(context).inflate(R.layout.layout_number_keyboard, this, true)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val button0 = findViewById<Button>(R.id.button0)
        val buttonDot = findViewById<Button>(R.id.buttonDot)
        val buttonDelete = findViewById<Button>(R.id.buttonBackSpace)

        // set button click listeners
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        button0.setOnClickListener(this)
        buttonDot.setOnClickListener(this)
        buttonDelete.setOnClickListener(this)

        // map buttons IDs to input strings
        keyValues.put(R.id.button1, context.getString(R.string._1))
        keyValues.put(R.id.button2, context.getString(R.string._2))
        keyValues.put(R.id.button3, context.getString(R.string._3))
        keyValues.put(R.id.button4, context.getString(R.string._4))
        keyValues.put(R.id.button5, context.getString(R.string._5))
        keyValues.put(R.id.button6, context.getString(R.string._6))
        keyValues.put(R.id.button7, context.getString(R.string._7))
        keyValues.put(R.id.button8, context.getString(R.string._8))
        keyValues.put(R.id.button9, context.getString(R.string._9))
        keyValues.put(R.id.button0, context.getString(R.string._0))
        keyValues.put(R.id.buttonDot, context.getString(R.string.dot))
    }

    override fun onClick(v: View) {

        // do nothing if the InputConnection has not been set yet
        if (inputConnection == null) return

        // Delete text or input key value
        // All communication goes through the InputConnection
        if (v.id == R.id.buttonBackSpace) {
            val selectedText = inputConnection!!.getSelectedText(0)
            if (TextUtils.isEmpty(selectedText)) {
                // no selection, so delete previous character
                inputConnection!!.deleteSurroundingText(1, 0)
            } else {
                // delete the selection
                inputConnection!!.commitText("", 1)
            }
        }
        else if (v.id == R.id.buttonDot) {
            val selectedText = inputConnection!!.getSelectedText(0)

            if (TextUtils.isEmpty(selectedText)) {

                val isContainsDot = inputConnection!!.getExtractedText(ExtractedTextRequest(), 0).text.contains(context.getString(R.string.dot))
                if(!isContainsDot){
                    val value = keyValues[v.id]
                    inputConnection!!.commitText(value, 1)
                }
            } else {
                val value = keyValues[v.id]
                inputConnection!!.commitText(value, 1)
            }

        }
        else {
            val value = keyValues[v.id]
            inputConnection!!.commitText(value, 1)
        }
    }

    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    fun setInputConnectionToEditText(ic: InputConnection?) {
        inputConnection = ic
    }
}