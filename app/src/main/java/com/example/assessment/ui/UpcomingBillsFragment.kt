package com.example.assessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessment.databinding.FragmentUpcomingBillsBinding
import com.example.assessment.model.UpcomingBill
import com.example.assessment.utils.Constants
import com.example.assessment.viewmodel.BillsViewModel


class UpcomingBillsFragment : Fragment(), onClickBill {
    private var binding:FragmentUpcomingBillsBinding? = null
    private val billsViewModel:BillsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingBillsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        getUpcomingBills()
    }
    private fun getUpcomingBills(){
        billsViewModel.getUpcomingBillsByFrequency(Constants.WEEKLY)
            .observe(this){weeklyBills->
                val adapter = UpcomingBillsAdapter(weeklyBills,this)
                binding?.rvweekly?.layoutManager=LinearLayoutManager(requireContext())
                binding?.rvweekly?.adapter = adapter
            }

        billsViewModel.getUpcomingBillsByFrequency(Constants.MONTHLY)
            .observe(this){monthlyBills->
                val adapter = UpcomingBillsAdapter(monthlyBills,this)
                binding?.rvmonthly?.layoutManager=LinearLayoutManager(requireContext())
                binding?.rvmonthly?.adapter=adapter
            }
        billsViewModel.getUpcomingBillsByFrequency(Constants.QUARTERLY)
            .observe(this){quarterlyBills->
                val adapter = UpcomingBillsAdapter(quarterlyBills,this)
                binding?.rvquartely?.layoutManager=LinearLayoutManager(requireContext())
                binding?.rvquartely?.adapter=adapter
            }

        billsViewModel.getUpcomingBillsByFrequency(Constants.ANNUAL)
            .observe(this){annualBills->
                val adapter = UpcomingBillsAdapter(annualBills,this)
                binding?.rvannual?.layoutManager=LinearLayoutManager(requireContext())
                binding?.rvannual?.adapter=adapter
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCheckBoxMarked(upcomingBill: UpcomingBill) {
        upcomingBill.paid=!upcomingBill.paid
        upcomingBill.synched=false

        billsViewModel.updateUpcomingBill(upcomingBill)
    }
}