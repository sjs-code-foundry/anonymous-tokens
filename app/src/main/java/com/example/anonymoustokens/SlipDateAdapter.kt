package com.example.anonymoustokens

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SlipDateAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var slipdates: MutableList<SlipDate> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlipdateViewHolder {
        return SlipdateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_slipdate, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is SlipdateViewHolder -> {
                holder.bind(slipdates.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return slipdates.size
    }

    fun submitList(slipdateList: List<SlipDate>) {
        slipdates = slipdateList.toMutableList()
    }

    class SlipdateViewHolder constructor(
        slipdateView: View
    ): RecyclerView.ViewHolder(slipdateView) {

        val slipdateField = slipdateView.findViewById<TextView>(R.id.tvSlipDate)
        val checkField = slipdateView.findViewById<CheckBox>(R.id.cbDelete)

        fun bind(slipDate: SlipDate) {

            slipdateField.setText(slipDate.date.toString())
            checkField.isChecked = slipDate.isChecked

        }

    }

    fun getSlipDates(): List<SlipDate> {
        return slipdates
    }

    fun deleteSelectedSlipDates(): List<SlipDate> {
        slipdates.removeAll { SlipDate ->
            SlipDate.isChecked
        }

        notifyDataSetChanged()

        return slipdates
    }

}

//class SlipDateAdapter (
//    private val slipDates: MutableList<SlipDate>
//) :RecyclerView.Adapter<SlipDateAdapter.SlipDateViewHolder>() {
//
//    inner class SlipDateViewHolder (val binding: ItemSlipdateBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlipDateViewHolder {
//        val binding = ItemSlipdateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//
//        Log.i("ViewHolderCreated", "True")
//
//        return SlipDateViewHolder(binding)
//    }
//
//    fun getSlipDates(): MutableList<SlipDate> {
//        Log.i("SlipDateList", slipDates.toString().toString())
//        // ^^^ These are correct, problem is with RecyclerView
//        return slipDates
//    }
//
//    fun addSlipDate(slipDate: SlipDate) {
//        slipDates.add(slipDate)
//        notifyItemInserted(slipDates.size - 1)
//    }
//
//    fun deleteSelectedSlipDates() {
//        slipDates.removeAll { slipDate ->
//            slipDate.isChecked
//        }
//        notifyDataSetChanged()
//    }
//
//    private fun toggleStrikeThrough(tvSlipDate: TextView, isChecked: Boolean) {
//        if(isChecked) {
//            tvSlipDate.paintFlags = tvSlipDate.paintFlags or STRIKE_THRU_TEXT_FLAG
//        } else {
//            tvSlipDate.paintFlags = tvSlipDate.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
//        }
//    }
//
//    override fun onBindViewHolder(holder: SlipDateViewHolder, position: Int) {
//        var curSlipDate = slipDates[position]
//        Log.i("curSlipDate", curSlipDate.toString())
//        // ^^^ curSlipDate should call for every item in list but sometimes only calls for most recent date
//        with(holder) {
//            holder.itemView.apply {
//                binding.tvSlipDate.text = curSlipDate.date
//                binding.cbDelete.isChecked = curSlipDate.isChecked
//                toggleStrikeThrough(binding.tvSlipDate, curSlipDate.isChecked)
//                binding.cbDelete.setOnCheckedChangeListener { _, isChecked ->
//                    toggleStrikeThrough(binding.tvSlipDate, isChecked)
//                    curSlipDate.isChecked = !curSlipDate.isChecked
//                }
//            }
//        }
//        Log.i("ViewHolderBound", "True")
//    }
//
//    override fun getItemCount(): Int {
//        return slipDates.size
//    }
//}