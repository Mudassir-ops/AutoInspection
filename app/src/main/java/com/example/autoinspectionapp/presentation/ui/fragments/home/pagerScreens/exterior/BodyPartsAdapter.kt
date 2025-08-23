package com.example.autoinspectionapp.presentation.ui.fragments.home.pagerScreens.exterior

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autoinspectionapp.databinding.ItemInputFieldBinding
import com.example.autoinspectionapp.presentation.uimodels.PartUiModel

class BodyPartsAdapter(
    private var items: List<PartUiModel>
) : RecyclerView.Adapter<BodyPartsAdapter.BodyPartViewHolder>() {
    fun String.toLabel(): String {
        return this.replace(Regex("([a-z])([A-Z])"), "$1 $2")
            .replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            }
    }

    inner class BodyPartViewHolder(val binding: ItemInputFieldBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PartUiModel) {
            binding.apply {
                tvLabel.text = item.partName.toLabel()
                etInput.setText(item.summary?.damageCodes?.joinToString { it.code })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodyPartViewHolder {
        val binding = ItemInputFieldBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BodyPartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BodyPartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<PartUiModel>) {
        items = newItems
        notifyDataSetChanged()
    }

}
