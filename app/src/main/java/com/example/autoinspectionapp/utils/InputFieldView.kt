package com.example.autoinspectionapp.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.example.autoinspectionapp.R
import com.google.android.material.textview.MaterialTextView

class InputFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val tvLabel: MaterialTextView
    val etInput: AppCompatEditText

    init {
        inflate(context, R.layout.item_input_field, this)
        tvLabel = findViewById(R.id.tvLabel)
        etInput = findViewById(R.id.etInput)

        context.theme.obtainStyledAttributes(attrs, R.styleable.InputFieldView, 0, 0).apply {
            try {
                val label = getString(R.styleable.InputFieldView_labelText)
                tvLabel.text = label
            } finally {
                recycle()
            }
        }

        setupStateListeners()
    }

    private fun setupStateListeners() {
        etInput.setOnFocusChangeListener { _, hasFocus ->
            updateBackground(hasFocus)
        }
        etInput.addTextChangedListener {
            updateBackground(etInput.hasFocus())
        }
        updateBackground(false)
    }

    private fun updateBackground(isFocused: Boolean) {
        val textEmpty = etInput.text.isNullOrBlank()

        when {
            isFocused -> etInput.setBackgroundResource(R.drawable.bg_input_selected)
            textEmpty -> etInput.setBackgroundResource(R.drawable.bg_input_empty)
            else -> etInput.setBackgroundResource(R.drawable.bg_input_unselected)
        }
    }
}

