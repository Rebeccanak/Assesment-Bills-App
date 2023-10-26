package com.example.assessment.ui

import android.os.Build
import com.example.assessment.databinding.UpcomingBillsListItemBinding
import com.example.assessment.model.UpcomingBill
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.assessment.utils.DateTimeUtils

class UpcomingBillsAdapter(var upcomingBill:List<UpcomingBill>, val onClickBill: onClickBill ):Adapter<UpcomingBillsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingBillsViewHolder {
        val binding=UpcomingBillsListItemBinding.inflate(LayoutInflater.from(parent.context))
        return  UpcomingBillsViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UpcomingBillsViewHolder, position: Int) {
        val upcomingBill=upcomingBill.get(position)
        holder.binding.apply {
            Checkbox.isChecked=upcomingBill.paid
            Checkbox.text=upcomingBill.name
            tvamount.text=upcomingBill.amount.toString()
            tvDuedate.text=DateTimeUtils.formatDateReadable(upcomingBill.dueDate)
        }
        holder.binding.Checkbox.setOnClickListener {
            onClickBill.onCheckBoxMarked(upcomingBill)
        }
    }

    override fun getItemCount(): Int {
        return upcomingBill.size

    }
}
class UpcomingBillsViewHolder(var binding: UpcomingBillsListItemBinding):ViewHolder(binding.root)


interface onClickBill{
    fun onCheckBoxMarked(upcomingBill: UpcomingBill){
    }
}
