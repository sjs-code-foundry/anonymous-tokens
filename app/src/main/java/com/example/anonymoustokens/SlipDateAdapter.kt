package com.example.anonymoustokens

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SlipDateAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var slipdates: MutableList<SlipDate> = ArrayList()

    class SlipdateViewHolder constructor(
        slipdateView: View
    ): RecyclerView.ViewHolder(slipdateView) {

        val slipdateField = slipdateView.findViewById<TextView>(R.id.tvSlipDate)
        val checkField = slipdateView.findViewById<CheckBox>(R.id.cbDelete)

        fun bind(slipDate: SlipDate) {

            slipdateField.setText(slipDate.date.toString())
            checkField.isChecked = slipDate.isChecked

            if (slipDate.isChecked) {
                slipdateField.paintFlags = slipdateField.paintFlags or STRIKE_THRU_TEXT_FLAG
            } else {
                slipdateField.paintFlags = slipdateField.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlipdateViewHolder {
        val binding = LayoutInflater.from(parent.context).inflate(R.layout.item_slipdate, parent, false)

        return SlipdateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SlipdateViewHolder -> {
                holder.bind(slipdates.get(position))

                holder.checkField.setOnClickListener {
                    slipdates.get(position).isChecked = !slipdates.get(position).isChecked
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return slipdates.size
    }

    fun submitList(slipdateList: List<SlipDate>) {
        slipdates = slipdateList.toMutableList()
    }

    fun getSlipDates(): List<SlipDate> {
        return slipdates
    }

    fun addSlipDate(slipDate: SlipDate) {
        slipdates.add(slipDate)
        notifyItemInserted(slipdates.size - 1)
    }

    fun deleteSelectedSlipDates(): List<SlipDate> {
        slipdates.removeAll { SlipDate ->
            SlipDate.isChecked
        }

        notifyDataSetChanged()

        return slipdates
    }

    fun sortByRecency(): List<SlipDate> {
        slipdates.sortByDescending { it.date }

        notifyDataSetChanged()

        return slipdates
    }

}