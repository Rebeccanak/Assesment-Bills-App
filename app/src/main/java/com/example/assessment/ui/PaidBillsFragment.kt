package com.example.assessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessment.databinding.FragmentPaidBillsBinding
import com.example.assessment.model.UpcomingBill
import com.example.assessment.viewmodel.BillsViewModel


class PaidBillsFragment : Fragment(), onClickBill{

    val billsViewModel:BillsViewModel by viewModels()
    var binding:FragmentPaidBillsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentPaidBillsBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        billsViewModel.getPaidBills().observe(this){paidBills->
            val adapter =UpcomingBillsAdapter(paidBills,this)
            binding?.rvpaid?.layoutManager=LinearLayoutManager(requireContext())
            binding?.rvpaid?.adapter=adapter

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding= null
        }

    override fun onCheckBoxMarked(upcomingBill: UpcomingBill) {
        upcomingBill.paid=false
        billsViewModel.updateUpcomingBill(upcomingBill)
    }
    }

