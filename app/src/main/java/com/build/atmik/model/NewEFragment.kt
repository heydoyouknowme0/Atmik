package com.build.atmik.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.build.atmik.R
import com.build.atmik.data.Entry
import com.build.atmik.data.ViewModel
import java.util.Date


class NewEFragment(journalIdPass:Long) : DialogFragment(R.layout.fragment_new_e) {
    private lateinit var mViewModel: ViewModel
    val journalid=journalIdPass
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val closeButton: Button = view.findViewById(R.id.closeButton)
        val createButton: Button = view.findViewById(R.id.createButton)
        mViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        val journalName: TextView = view.findViewById(R.id.nameofJournal)
        closeButton.setOnClickListener {
            dismiss()
        }
        createButton.setOnClickListener {
            if(journalName.text.isNotBlank()){
                insertDataToDatabase(journalName.text.toString())
                dismiss()
            }
        }
    }
    private fun insertDataToDatabase(newName:String) {
        val entry=Entry(0, Date().toString(),newName, journalId = journalid, data = "")
        mViewModel.addEntry(entry)
    }
}