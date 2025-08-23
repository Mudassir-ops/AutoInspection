package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.tyres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autoinspectionapp.databinding.ImageItemBinding

class ImageAdapterTyres(
    private val onAddImageClick: (Int) -> Unit,
    private val onImageClick: (String) -> Unit,
    private val adapterId: Int = 0
) : RecyclerView.Adapter<ImageAdapterTyres.BaseViewHolder>() {

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
        val binding = ImageItemBinding.inflate(inflater, parent, false)
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
        protected val binding: ImageItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    inner class ImageViewHolder(binding: ImageItemBinding) : BaseViewHolder(binding) {
        fun bind(path: String) = with(binding) {
            binding.imagePath = path
            binding.executePendingBindings()
            ivActualImage.setOnClickListener { onImageClick(path) }
        }
    }

    inner class PlaceholderViewHolder(binding: ImageItemBinding) : BaseViewHolder(binding) {
        fun bind() = with(binding) {
            imagePath = null
            executePendingBindings()
            ivPlaceHolder.setOnClickListener { onAddImageClick(adapterId) }
        }
    }
}
