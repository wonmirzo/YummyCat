package com.wonmirzo.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.wonmirzo.R
import com.wonmirzo.model.FilterItem
import com.wonmirzo.utils.Logger
import java.lang.RuntimeException

class FilterFragment : Fragment(R.layout.fragment_filter) {
    private lateinit var btnSubmit: Button
    private lateinit var listener: FilterSend

    private var order = ""
    private var category = ""
    private var type = ""
    private var breed = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
    }

    private fun initView(view: View) {
        btnSubmit = view.findViewById(R.id.btnSubmit)

        val spOrder: Spinner = view.findViewById(R.id.spOrder)
        val spCategory: Spinner = view.findViewById(R.id.spCategory)
        val spType: Spinner = view.findViewById(R.id.spType)
        val spBreed: Spinner = view.findViewById(R.id.spBreed)

        orderConfig(spOrder)
        categoryConfig(spCategory)
        typeConfig(spType)
        breedConfig(spBreed)

        btnSubmit.setOnClickListener {
            val filterItem = FilterItem(order, category, type, breed)
            listener.onFilterSend(filterItem)
            submitFilter(filterItem)
        }
    }

    private fun submitFilter(item: FilterItem) {
        updateFilter(item)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.flFragment, SearchFragment())
            .commit()
    }

    private fun updateFilter(item: FilterItem) {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FilterSend) {
            listener = context
        } else {
            throw RuntimeException("$context Must implement FilterSend")
        }
    }

    override fun onDetach() {

        super.onDetach()
        listener = null!!
    }

    private fun orderConfig(spOrder: Spinner) {
        val orders = listOf("Random", "Desc", "Asc")
        val adapter = ArrayAdapter<String>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            orders
        )
        spOrder.adapter = adapter
        spOrder.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                order = adapterView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun categoryConfig(spCategory: Spinner) {
        val categories =
            listOf("None", "Boxes", "Clothes", "Hats", "Sinks", "Space", "Sunglasses", "Ties")
        val adapter = ArrayAdapter<String>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            categories
        )
        spCategory.adapter = adapter
        spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                category = adapterView?.getItemAtPosition(position).toString()
//                Logger.d(
//                    "@@@",
//                    "You selected ${adapterView?.getItemAtPosition(position).toString()}"
//                )
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun typeConfig(spType: Spinner) {
        val types = listOf("All", "Static", "Animated")
        val adapter = ArrayAdapter<String>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            types
        )
        spType.adapter = adapter
        spType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                type = adapterView?.getItemAtPosition(position).toString()
//                Logger.d(
//                    "@@@",
//                    "You selected ${adapterView?.getItemAtPosition(position).toString()}"
//                )
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun breedConfig(spBreed: Spinner) {
        val breeds =
            listOf(
                "None",
                "Abyssinian",
                "Aegean",
                "Bengal",
                "Birman",
                "Cheetoh",
                "Cymric",
                "Donskoy"
            )
        val adapter = ArrayAdapter<String>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            breeds
        )
        spBreed.adapter = adapter
        spBreed.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                breed = adapterView?.getItemAtPosition(position).toString()
//                Logger.d(
//                    "@@@",
//                    "You selected ${adapterView?.getItemAtPosition(position).toString()}"
//                )
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    interface FilterSend {
        fun onFilterSend(filter: FilterItem)
    }
}