package com.asifddlks.customnumerickeyboard

import android.annotation.SuppressLint
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.widget.NestedScrollView


//
// Created by Asif Ahmed on 12/1/23.
// Copyright (c) 2023 . All rights reserved.
//
@SuppressLint("ClickableViewAccessibility")
fun EditText.setCustomNumericKeyboard(
    textColor: Int,
    hasDotKey: Boolean = true,
    keyboard: CustomNumericKeyboard,
    nestedScrollView: NestedScrollView
) {
    this.setRawInputType(InputType.TYPE_CLASS_NUMBER)
    this.setTextIsSelectable(true)
    this.showSoftInputOnFocus = false
    keyboard.visibility = View.GONE

    this.setOnClickListener {
        keyboard.slideUp()
        val inputConnection = this.onCreateInputConnection(EditorInfo())
        keyboard.setAttribute(inputConnection,textColor, hasDotKey)
    }

    this.setOnFocusChangeListener { view, hasFocus ->
        Log.d("hasFocus", "hasFocus: $hasFocus")
        if (hasFocus) {
            keyboard.slideUp()
            val inputConnection = this.onCreateInputConnection(EditorInfo())
            keyboard.setAttribute(inputConnection,textColor, hasDotKey)
            //keyboard.setInputConnectionToEditText()
        } else {
            //keyboard.slideDown()
        }
    }

    nestedScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
        if (kotlin.math.abs(scrollY - oldScrollY) > 0) {
            keyboard.slideDown()
            this.clearFocus()
        }
    }
}

fun View.slideUp(duration: Long = 300) {
    this.visibility = View.VISIBLE
    this.animate().translationY(0f).setDuration(duration).start()
}

fun View.slideDown(duration: Long = 300) {
    this.animate().translationY(this.y).setDuration(duration).start()
    this.visibility = View.GONE
}
