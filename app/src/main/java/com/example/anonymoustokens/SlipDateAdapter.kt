package com.example.anonymoustokens

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_slipdate.view.cbDelete
import kotlinx.android.synthetic.main.item_slipdate.view.tvSlipDate

class SlipDateAdapter (
    private val slipDates: MutableList<SlipDate>
) :RecyclerView.Adapter<SlipDateAdapter.SlipDateViewHolder>() {

    class SlipDateViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlipDateViewHolder {
        return SlipDateViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_slipdate,
                parent,
                false
            )
        )
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
        holder.itemView.apply {
            tvSlipDate.text = curSlipDate.date.toString()
            cbDelete.isChecked = curSlipDate.isChecked
            toggleStrikeThrough(tvSlipDate, curSlipDate.isChecked)
            cbDelete.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvSlipDate, isChecked)
                curSlipDate.isChecked = !curSlipDate.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return slipDates.size
    }
}