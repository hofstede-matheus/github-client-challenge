package com.hofstedematheus.githubclientchallenge.core.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

fun EditText.addDebouncedTextListener(
    debounceDuration: Long,
    lifecycle: Lifecycle,
    onChanged: (String) -> Unit
) = addTextChangedListener(object: TextWatcher {

    private var job: Job? = null
    private val coroutineScope: CoroutineScope = lifecycle.coroutineScope

    override fun afterTextChanged(newText: Editable?) { }

    override fun beforeTextChanged(newText: CharSequence?, start: Int, before: Int, count: Int) { }

    override fun onTextChanged(newText: CharSequence?, start: Int, before: Int, count: Int) {
        job?.cancel()
        job = coroutineScope.launch {
            newText?.let {
                delay(debounceDuration)
                onChanged(newText.toString())
            }
        }
    }
})