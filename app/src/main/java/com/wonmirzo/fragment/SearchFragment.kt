package com.wonmirzo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wonmirzo.R
import com.wonmirzo.adapter.SearchItemAdapter
import com.wonmirzo.model.FilterItem
import com.wonmirzo.network.RetrofitHttp
import com.wonmirzo.network.model.SearchItem
import com.wonmirzo.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var items: ArrayList<SearchItem>
    private lateinit var searchItemAdapter: SearchItemAdapter

    private val result = HashMap<String, String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
    }

    private fun initViews(view: View) {
        initFilter()
        val ivFilter: ImageView = view.findViewById(R.id.ivFilter)
        ivFilter.setOnClickListener {
            openFilterFragment()
        }

        val spinnerPerPage: Spinner = view.findViewById(R.id.spinnerPerPage)
        perPageConfig(spinnerPerPage)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)

        items = ArrayList()
        apiPhotoList()
        refreshSearchItemAdapter(items)
    }

    private fun initFilter() {
        result["order"] = "Rand"
        result["breed_ids"] = "abys"
        result["category_ids"] = "1"
    }

    private fun refreshSearchItemAdapter(items: ArrayList<SearchItem>) {
        searchItemAdapter = SearchItemAdapter(items)
        recyclerView.adapter = searchItemAdapter
    }

    private fun apiPhotoList() {
        RetrofitHttp.searchService.listPost(result).enqueue(object : Callback<List<SearchItem>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<SearchItem>>,
                response: Response<List<SearchItem>>
            ) {
                if (!response.isSuccessful) {
                    Logger.e("@@@", "Code: ${response.code()}")
                    return
                }
                items.clear()
                items.addAll(response.body()!!)
                searchItemAdapter.notifyDataSetChanged()
                Logger.d("@@@", "photos size: " + response.body()!!.size.toString())
            }

            override fun onFailure(call: Call<List<SearchItem>>, t: Throwable) {
                Logger.e("@@@", "error ${t.message}")
            }
        })
    }

    private fun perPageConfig(spinnerPerPage: Spinner) {
        val pages = listOf("10", "20", "25")
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            pages
        )
        spinnerPerPage.adapter = adapter
        spinnerPerPage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                result["limit"] = adapterView?.getItemAtPosition(position).toString()
                apiPhotoList()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun openFilterFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.flFragment, FilterFragment())
            .addToBackStack(null)
            .commit()
    }
}