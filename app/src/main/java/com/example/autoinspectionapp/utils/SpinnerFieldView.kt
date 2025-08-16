package com.example.autoinspectionapp.utils

import android.content.Context
import android.util.AttributeSet
import android.util.Log
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
                val selected = parent?.getItemAtPosition(position) as? String ?: return
                Log.d("SpinnerFieldView", "Selected item: $selected")

                if (selected.equals("N/A", ignoreCase = true)) {
                    spinnerBg.setBackgroundResource(R.drawable.bg_input_empty)
                } else {
                    spinnerBg.setBackgroundResource(R.drawable.bg_input_unselected)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    /**
     * Set spinner items and optionally a default value
     */
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
            spnValues.post {
                val idx = items.indexOfFirst { it.equals(default, ignoreCase = true) }
                if (idx != -1) {
                    spnValues.setSelection(idx)
                }
            }
        }
    }

    /**
     * Get the currently selected spinner item safely
     */
    val selectedItem: String?
        get() {
            val spinnerValue =
                spnValues.selectedItemPosition.takeIf { it != AdapterView.INVALID_POSITION }
                    ?.let { spnValues.getItemAtPosition(it) as? String }
            Log.d("SpinnerFieldView", "getSelectedItem: $spinnerValue")
            return spinnerValue
        }

}
