package com.example.autoinspectionapp.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.autoinspectionapp.R
import com.google.android.material.textview.MaterialTextView

class SpinnerFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val tvLabel: MaterialTextView
    val spnValues: AppCompatSpinner
    private val spinnerBg: View

    init {
        inflate(context, R.layout.item_spinner_field, this)
        tvLabel = findViewById(R.id.tvLabel)
        spnValues = findViewById(R.id.spnValues)
        spinnerBg = findViewById(R.id.spinnerBg)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SpinnerFieldView,
            0,
            0
        ).apply {
            try {
                val label = getString(R.styleable.SpinnerFieldView_labelTextSpinner)
                tvLabel.text = label
            } finally {
                recycle()
            }
        }

        spnValues.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selected = spnValues.selectedItem as String
                if (selected.equals("N/A", ignoreCase = true)) {
                    spinnerBg.setBackgroundResource(R.drawable.bg_input_empty)
                } else {
                    spinnerBg.setBackgroundResource(R.drawable.bg_input_unselected)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun setItems(items: List<String>, defaultValue: String? = null) {
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            items
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spnValues.adapter = adapter

        defaultValue?.let { default ->
            val idx = items.indexOf(default)
            if (idx != -1) {
                spnValues.setSelection(idx)
            }
        }
    }


    fun getSelectedItem(): String {
        return spnValues.selectedItem as String
    }


}
