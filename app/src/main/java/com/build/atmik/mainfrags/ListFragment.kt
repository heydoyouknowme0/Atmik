package com.build.atmik.mainfrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.build.atmik.R
import com.build.atmik.data.ViewModel
import com.build.atmik.model.ListAdapter
import com.build.atmik.model.NewEFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val ARG_PARAM1 = "param1"

class ListFragment(journalId:Long) : Fragment() {
    private val journalId=journalId
    private lateinit var mViewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter = ListAdapter(::onRowClick)
        val addEntry= view.findViewById<FloatingActionButton>(R.id.addEntry)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        mViewModel.getEntriesForJournal(journalId).observe(viewLifecycleOwner, Observer { entries ->
            adapter.setData(entries)
        })
        addEntry.setOnClickListener {
            val dialogFragment = NewEFragment(journalId)
            dialogFragment.show(parentFragmentManager, "MyDialogFragment")}
        return view
    }

    fun onRowClick(journalId: Long) {
        val nextFragment = DiaryFragment(journalId)
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView2, nextFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}