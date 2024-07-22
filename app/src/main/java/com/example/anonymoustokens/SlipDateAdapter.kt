package com.example.anonymoustokens

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anonymoustokens.databinding.ItemSlipdateBinding

class SlipDateAdapter (
    private val slipDates: MutableList<SlipDate>
) :RecyclerView.Adapter<SlipDateAdapter.SlipDateViewHolder>() {

    inner class SlipDateViewHolder (val binding: ItemSlipdateBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlipDateViewHolder {
        val binding = ItemSlipdateBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SlipDateViewHolder(binding)
    }

    fun getSlipDates(): MutableList<SlipDate> {
        return slipDates
    }

    fun addSlipDate(slipDate: SlipDate) {
        slipDates.add(slipDate)
        notifyItemInserted(slipDates.size - 1)
    }

    fun deleteSelectedSlipDates() {
        slipDates.removeAll { slipDate ->
            slipDate.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvSlipDate: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvSlipDate.paintFlags = tvSlipDate.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvSlipDate.paintFlags = tvSlipDate.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: SlipDateViewHolder, position: Int) {
        var curSlipDate = slipDates[position]
        with(holder) {
            holder.itemView.apply {
                binding.tvSlipDate.text = curSlipDate.date
                binding.cbDelete.isChecked = curSlipDate.isChecked
                toggleStrikeThrough(binding.tvSlipDate, curSlipDate.isChecked)
                binding.cbDelete.setOnCheckedChangeListener { _, isChecked ->
                    toggleStrikeThrough(binding.tvSlipDate, isChecked)
                    curSlipDate.isChecked = !curSlipDate.isChecked
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return slipDates.size
    }
}