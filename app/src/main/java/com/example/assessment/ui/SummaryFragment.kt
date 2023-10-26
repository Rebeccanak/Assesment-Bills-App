package com.example.assessment.ui
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.graphics.vector.SolidFill
import com.example.assessment.databinding.FragmentSummaryBinding
import com.example.assessment.model.BillsSummary
import com.example.assessment.utils.Utils
import com.example.assessment.viewmodel.BillsViewModel

class SummaryFragment : Fragment() {
    var binding: FragmentSummaryBinding?=null
    val billsViewModel:BillsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSummaryBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        binding?.fabAddBill?.setOnClickListener {
            startActivity(Intent(requireContext(),AddBillActivity::class.java))
        }
        billsViewModel.getMonthlySummary()
        showMonthlySummary()
    }
    fun showMonthlySummary(){
        billsViewModel.summaryLiveData.observe(this){summary->
            binding?.tvPaidAmt?.text=Utils.formatCurrency(summary.paid)
            binding?.tvOverdueAmt?.text=Utils.formatCurrency(summary.overdue)
            binding?.tvPendingAmt?.text=Utils.formatCurrency(summary.upcoming)
            binding?.tvTotalAmt?.text=Utils.formatCurrency(summary.total)
            ShowChart(summary)
        }
    }
    fun ShowChart(summary:BillsSummary){
        val entries = mutableListOf<DataEntry>()
        entries.add(ValueDataEntry("Paid",summary.paid))
        entries.add(ValueDataEntry("Upcoming",summary.upcoming))
        entries.add(ValueDataEntry("Overdue",summary.overdue))

        val pieChart = AnyChart.pie()
        pieChart.data(entries)
        pieChart.innerRadius(80)
        pieChart.palette().itemAt(0, SolidFill("#008000",100))
        pieChart.palette().itemAt(1, SolidFill("#FF00FF",100))
        pieChart.palette().itemAt(2, SolidFill("#0000FF",100))
        binding?.summaryChart?.setChart(pieChart)


    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}