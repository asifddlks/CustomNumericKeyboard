package com.asifddlks.customnumerickeyboard

import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.EditText

//
// Created by Asif Ahmed on 12/1/23.
// Copyright (c) 2023 . All rights reserved.
//
fun EditText.setCustomNumericKeyboard(keyboard: CustomNumericKeyboard) {
    this.setRawInputType(InputType.TYPE_CLASS_NUMBER)
    this.setTextIsSelectable(true)
    this.showSoftInputOnFocus = false

    val ic = this.onCreateInputConnection(EditorInfo())
    keyboard.setInputConnectionToEditText(ic)
}