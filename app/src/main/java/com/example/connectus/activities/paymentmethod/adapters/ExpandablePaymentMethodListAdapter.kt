package com.example.connectus.activities.paymentmethod.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.connectus.activities.paymentmethod.models.PaymentData
import com.example.connectus.activities.paymentmethod.models.PaymentMethodData
import com.example.connectus.databinding.PaymentmethodMethodGroupBinding
import com.example.connectus.databinding.PaymentmethodPaymentItemBinding

class ExpandablePaymentMethodListAdapter(
    private val context: Context,
    private val data: List<PaymentMethodData>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return data.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return data[groupPosition].paymentItemList.size
    }

    override fun getGroup(groupPosition: Int): PaymentMethodData {
        return data[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): PaymentData {
        return data[groupPosition].paymentItemList[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val binding =
            PaymentmethodMethodGroupBinding.inflate(LayoutInflater.from(context), parent, false)

        binding.listGroup.setCompoundDrawablesWithIntrinsicBounds(getGroup(groupPosition).image, 0, 0, 0)
        binding.listGroup.text = getGroup(groupPosition).name
        return binding.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val binding =
            PaymentmethodPaymentItemBinding.inflate(LayoutInflater.from(context), parent, false)

        binding.listItem.setCompoundDrawablesWithIntrinsicBounds(getChild(groupPosition, childPosition).image, 0, 0, 0)
        binding.listItem.text = getChild(groupPosition, childPosition).name
        return binding.root
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}