package com.hofstedematheus.githubclientchallenge.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

fun ViewGroup.inflate(layoutId: Int): View = LayoutInflater.from(context).inflate(layoutId, this, false)

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

infix fun View?.isVisibleIf(shouldShow: Boolean) {
    if (shouldShow) this?.visible()
    else this?.gone()
}

infix fun EditText?.isEnabledIf(shouldBeEnabled: Boolean) {
    this?.apply {
        isEnabled = shouldBeEnabled
    }
}