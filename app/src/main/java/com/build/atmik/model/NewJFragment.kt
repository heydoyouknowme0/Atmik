package com.build.atmik.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider

import com.build.atmik.R
import com.build.atmik.data.Journal
import com.build.atmik.data.ViewModel


class NewJFragment : DialogFragment() {
    private lateinit var mViewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_new_j, container, false)
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

        return view
    }

    private fun insertDataToDatabase(newName:String) {
        val journal= Journal(0,newName)
        mViewModel.addJournal(journal)
    }

}