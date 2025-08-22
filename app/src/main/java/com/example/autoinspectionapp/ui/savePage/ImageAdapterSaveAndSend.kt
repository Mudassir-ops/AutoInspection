package com.example.autoinspectionapp.ui.savePage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autoinspectionapp.databinding.ImageItemBinding
import com.example.autoinspectionapp.databinding.ImageItemFullBinding
import com.example.autoinspectionapp.databinding.ImageItemFullBindingImpl

class ImageAdapterSaveAndSend(
    private val onAddImageClick: () -> Unit,
    private val onImageClick: (String) -> Unit,
) : RecyclerView.Adapter<ImageAdapterSaveAndSend.BaseViewHolder>() {

    private val images = mutableListOf<String>()

    companion object {
        private const val TYPE_IMAGE = 0
        private const val TYPE_PLACEHOLDER = 1
    }

    override fun getItemCount(): Int = images.size + 1

    override fun getItemViewType(position: Int): Int =
        if (position < images.size) TYPE_IMAGE else TYPE_PLACEHOLDER

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageItemFullBinding.inflate(inflater, parent, false)
        return if (viewType == TYPE_IMAGE) {
            ImageViewHolder(binding)
        } else {
            PlaceholderViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder -> holder.bind(images[position])
            is PlaceholderViewHolder -> holder.bind()
        }
    }

    fun addImage(path: String) {
        images.add(path)
        notifyItemInserted(images.size - 1)
    }

    fun removeImage(path: String) {
        val index = images.indexOf(path)
        if (index != -1) {
            images.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    abstract class BaseViewHolder(
        protected val binding: ImageItemFullBinding
    ) : RecyclerView.ViewHolder(binding.root)

    inner class ImageViewHolder(binding: ImageItemFullBinding) : BaseViewHolder(binding) {
        fun bind(path: String) = with(binding) {
            binding.imagePath = path
            binding.executePendingBindings()
            ivActualImage.setOnClickListener { onImageClick(path) }
        }
    }

    inner class PlaceholderViewHolder(binding: ImageItemFullBinding) : BaseViewHolder(binding) {
        fun bind() = with(binding) {
            imagePath = null
            executePendingBindings()
            ivPlaceHolder.setOnClickListener { onAddImageClick() }
        }
    }
}
