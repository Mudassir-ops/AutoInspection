package com.example.autoinspectionapp.presentation.dialog


import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.autoinspectionapp.databinding.DialogImageviewBinding
import com.example.autoinspectionapp.databinding.PhotoDialogBinding
import com.example.autoinspectionapp.databinding.ProgressItemLayoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

inline fun Fragment.showImageDialog(
    imagePath: String,
    crossinline deleteImage: () -> Unit
) {
    val binding = DialogImageviewBinding.inflate(LayoutInflater.from(this.context ?: return))
    val imageDialog = Dialog(this.context ?: return)
    imageDialog.run {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        this.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
        )
        val params = WindowManager.LayoutParams()
        params.copyFrom(window?.attributes)
        val displayMetrics = context.resources.displayMetrics
        val horizontalMargin = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._32sdp)
        params.width = displayMetrics.widthPixels - 2 * horizontalMargin
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = params
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        show()
    }

    binding.apply {
        binding.imagePath = imagePath
        closeButton.setOnClickListener {
            imageDialog.dismiss()
        }
        ivDelete.setOnClickListener {
            imageDialog.dismiss()
            deleteImage()
        }

    }
}


inline fun Fragment.showImagePickerDialog(
    crossinline pickFromCamera: () -> Unit,
    crossinline pickFromGallery: () -> Unit
) {
    val binding = PhotoDialogBinding.inflate(LayoutInflater.from(this.context ?: return))
    val photoDialog = Dialog(this.context ?: return)
    photoDialog.run {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        this.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
        )
        val params = WindowManager.LayoutParams()
        params.copyFrom(window?.attributes)
        val displayMetrics = context.resources.displayMetrics
        val horizontalMargin = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._10sdp)
        params.width = displayMetrics.widthPixels - 2 * horizontalMargin
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = params
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        show()
    }
    binding.apply {
        viewGallery.setOnClickListener {
            pickFromGallery.invoke()
            photoDialog.dismiss()
        }
        viewCamera.setOnClickListener {
            pickFromCamera.invoke()
            photoDialog.dismiss()
        }
    }
}

fun showTyreSeekBar(
    context: Context,
    initialValue: Int = 0,
    maxValue: Int = 100,
    onValueSelected: (Int) -> Unit
) {
    // Create a SeekBar programmatically
    val seekBar = SeekBar(context).apply {
        max = maxValue
        progress = initialValue
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).also { it.setMargins(32, 16, 32, 16) }
    }

    // Optional: TextView to show current value
    val valueText = TextView(context).apply {
        text = "$initialValue"
        textSize = 16f
        setPadding(32, 16, 32, 16)
    }

    seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
            valueText.text = "$progress"
        }

        override fun onStartTrackingTouch(sb: SeekBar?) {}
        override fun onStopTrackingTouch(sb: SeekBar?) {}
    })

    // Container layout
    val container = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        addView(valueText)
        addView(seekBar)
    }

    // Show dialog
    MaterialAlertDialogBuilder(context)
        .setTitle("Tyre Damage Level")
        .setView(container)
        .setPositiveButton("OK") { dialog, _ ->
            onValueSelected(seekBar.progress)
            dialog.dismiss()
        }
        .setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}

