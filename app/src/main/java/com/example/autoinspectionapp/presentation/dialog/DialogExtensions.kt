package com.example.autoinspectionapp.presentation.dialog


import android.app.Dialog
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.autoinspectionapp.databinding.DialogImageviewBinding

inline fun Fragment?.showImageDialog(
    imagePath: String,
    crossinline deleteImage: () -> Unit
) {
    val binding = DialogImageviewBinding.inflate(LayoutInflater.from(this?.context ?: return))
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