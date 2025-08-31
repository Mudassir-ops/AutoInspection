package com.example.autoinspectionapp.presentation.customviews

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.autoinspectionapp.R
import com.example.autoinspectionapp.domain.SpinnerFieldListener
import com.google.android.material.textview.MaterialTextView

class SpinnerFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var listener: SpinnerFieldListener? = null

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
                if (selected.equals("Name", ignoreCase = true)) {
                    listener?.onSpecialItemSelected(item = "Name")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    /**
     * Set spinner items and optionally a default value
     */
    fun setItems(items: List<String>, defaultValue: String? = null) {
        val spinnerItems = buildList {
            addAll(items)
            if (defaultValue != null && items.none { it.equals(defaultValue, ignoreCase = true) }) {
                add(0, defaultValue) // put custom value at the top
            }
            if ("N/A" !in this) add("N/A")
        }

        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            spinnerItems
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spnValues.adapter = adapter

        spnValues.post {
            val defaultIdx = defaultValue?.let { value ->
                spinnerItems.indexOfFirst { it.equals(value, ignoreCase = true) }
            } ?: spinnerItems.lastIndex

            spnValues.setSelection(if (defaultIdx != -1) defaultIdx else spinnerItems.lastIndex)
        }
    }

    /**
     * Get the currently selected spinner item safely
     */
    val selectedItem: String?
        get() {
            val spinnerValue =
                spnValues.selectedItemPosition.takeIf { it != AdapterView.INVALID_POSITION }
                    ?.let { spnValues.getItemAtPosition(it) as? String } ?: "N/A"
            Log.d("SpinnerFieldView", "getSelectedItem: $spinnerValue")
            return spinnerValue
        }

}
