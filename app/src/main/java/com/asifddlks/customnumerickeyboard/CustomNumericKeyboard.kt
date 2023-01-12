package com.asifddlks.customnumerickeyboard

import android.content.Context
import android.content.res.ColorStateList
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import android.widget.LinearLayout
import com.asifddlks.customnumerickeyboard.databinding.LayoutNumberKeyboardBinding


//
// Created by Asif Ahmed on 12/1/23.
//
class CustomNumericKeyboard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var _binding: LayoutNumberKeyboardBinding? = null
    private val binding get() = _binding!!

    private var keyValues = SparseArray<String>()

    // Our communication link to the EditText
    private var inputConnection: InputConnection? = null

    init {
        init(context)
    }

    private fun init(context: Context) {

        // initialize buttons
        _binding = LayoutNumberKeyboardBinding.inflate(LayoutInflater.from(context), this,true)

        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)
        binding.button5.setOnClickListener(this)
        binding.button6.setOnClickListener(this)
        binding.button7.setOnClickListener(this)
        binding.button8.setOnClickListener(this)
        binding.button9.setOnClickListener(this)
        binding.button0.setOnClickListener(this)
        binding.buttonDot.setOnClickListener(this)
        binding.buttonDelete.setOnClickListener(this)

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
        if (v.id == R.id.buttonDelete) {
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

    fun setAttribute(textColor: Int, hasDotKey: Boolean) {
        binding.button1.setTextColor(context.getColor(textColor))
        binding.button2.setTextColor(context.getColor(textColor))
        binding.button3.setTextColor(context.getColor(textColor))
        binding.button4.setTextColor(context.getColor(textColor))
        binding.button5.setTextColor(context.getColor(textColor))
        binding.button6.setTextColor(context.getColor(textColor))
        binding.button7.setTextColor(context.getColor(textColor))
        binding.button8.setTextColor(context.getColor(textColor))
        binding.button9.setTextColor(context.getColor(textColor))
        binding.button0.setTextColor(context.getColor(textColor))
        binding.buttonDot.setTextColor(context.getColor(textColor))
        binding.buttonDelete.imageTintList = ColorStateList.valueOf(context.getColor(textColor))

        binding.buttonDot.visibility = if(hasDotKey) View.VISIBLE else View.VISIBLE
    }
}